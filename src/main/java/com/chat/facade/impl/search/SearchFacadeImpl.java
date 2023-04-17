package com.chat.facade.impl.search;

import com.chat.dto.plain.ChatDto;
import com.chat.dto.plain.UserDto;
import com.chat.dto.request.AllChatsRetrievalRequestDto;
import com.chat.dto.request.ChatListRetrievalRequestDto;
import com.chat.dto.request.SearchRequestDto;
import com.chat.dto.request.UserListRetrievalRequestDto;
import com.chat.dto.response.AllChatsRetrievalResponseDto;
import com.chat.dto.response.ChatListRetrievalResponseDto;
import com.chat.dto.response.SearchResponseDto;
import com.chat.dto.response.UserListRetrievalResponseDto;
import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;
import com.chat.entity.chat.type.ChatType;
import com.chat.entity.user.User;
import com.chat.facade.core.search.SearchFacade;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.chat.UserChatService;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SearchFacadeImpl implements SearchFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchFacadeImpl.class);

    private final UserService userService;
    private final UserChatService userChatService;
    private final ChatService chatService;

    public SearchFacadeImpl(UserService userService, UserChatService userChatService, ChatService chatService) {
        this.userService = userService;
        this.userChatService = userChatService;
        this.chatService = chatService;
    }

    @Override
    public UserListRetrievalResponseDto retrieveMultipleUsers(UserListRetrievalRequestDto requestDto) {
        LOGGER.info("Retrieving UserListRetrievalResponseDto according to the UserListRetrievalRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "UserListRetrievalRequestDto must not be null");

        String keyWord = requestDto.getKeyWord();
        String retrieverUsername = requestDto.getRetrieverUsername();

        if(userService.findByUsername(retrieverUsername).isEmpty()) {
            return new UserListRetrievalResponseDto(List.of(String.format("No user found having a username of %s", retrieverUsername)));
        }

        Set<UserDto> userDtoSet = new HashSet<>();

        for(User user : userService.findUsersWithSimilarUsernames(keyWord)) {
            userDtoSet.add(new UserDto(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getSecondName(),
                    user.getJoinedAt()
            ));
        }

        for(User user : userService.findUsersWithSimilarFirstNames(keyWord)) {
            userDtoSet.add(new UserDto(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getSecondName(),
                    user.getJoinedAt()
            ));
        }

        for(User user : userService.findUsersWithSimilarSecondNames(keyWord)) {
            userDtoSet.add(new UserDto(
                    user.getUsername(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getSecondName(),
                    user.getJoinedAt()
            ));
        }

        UserListRetrievalResponseDto responseDto = new UserListRetrievalResponseDto(
                new LinkedList<>(userDtoSet),
                LocalDateTime.now()
        );

        LOGGER.info("Successfully retrieved a UserListRetrievalResponseDto according to the UserListRetrievalRequestDto - {}, result - {}", requestDto, responseDto);
        return responseDto;

    }

    @Override
    public AllChatsRetrievalResponseDto retrieveAllChats(AllChatsRetrievalRequestDto requestDto) {
        LOGGER.info("Retrieving chats of a user according to the ChatListRetrievalRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "ChatListRetrievalRequestDto must not be null");

        String retrieverUsername = requestDto.getRetrieverUsername();

        Optional<User> optionalUser = userService.findByUsername(retrieverUsername);
        if(optionalUser.isEmpty()) {
            return new AllChatsRetrievalResponseDto(List.of(
                    String.format("User with username %s does not exist", retrieverUsername)
            ));
        }

        User user = optionalUser.get();
        List<ChatDto> chatDtos = userChatService.getAllUserChatsByUserId(user.getId()).stream().map(UserChat::getChat).map(chat -> new ChatDto(chat.getName(), chat.getChatType(), chat.getCreatedAt())).collect(Collectors.toList());
        AllChatsRetrievalResponseDto responseDto = new AllChatsRetrievalResponseDto(user.getId(), chatDtos, LocalDateTime.now());

        LOGGER.info("Successfully retrieved all chats of a user according to the ChatListRetrievalRequestDto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }

    @Override
    public ChatListRetrievalResponseDto retrieveMultipleChats(ChatListRetrievalRequestDto requestDto) {
        LOGGER.info("Retrieving a ChatListRetrievalResponseDto according to the ChatListRetrievalRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "Request Dto must not be null");

        String keyWord = requestDto.getKeyWord();
        String retrieverUsername = requestDto.getRetrieverUsername();

        if(userService.findByUsername(retrieverUsername).isEmpty()) {
            return new ChatListRetrievalResponseDto(List.of(String.format("No user found having a username of %s", retrieverUsername)));
        }

        List<Chat> chatList = chatService.findChatsWithSimilarNames(keyWord);
        List<ChatDto> chatDtoList = new LinkedList<>();

        for(Chat chat : chatList) {
            List<String> usernames = chat.getUsersInChat().stream().map(userChat -> userChat.getUser().getUsername()).collect(Collectors.toList());
            if(chat.getChatType() == ChatType.PERSONAL) {
                String otherThanRetrieverUsername = usernames.get(0).equals(retrieverUsername) ? usernames.get(1) : usernames.get(0);
                if(!otherThanRetrieverUsername.contains(keyWord)) {
                    continue;
                }
            }
            if(usernames.contains(retrieverUsername)) {
                chatDtoList.add(new ChatDto(
                        chat.getName(),
                        chat.getChatType(),
                        chat.getCreatedAt()
                ));
            }
        }

        ChatListRetrievalResponseDto responseDto = new ChatListRetrievalResponseDto(
                retrieverUsername,
                keyWord,
                chatDtoList,
                LocalDateTime.now()
        );

        LOGGER.info("Successfully retrieved a ChatListRetrievalResponseDto - {}, according to the ChatListRetrievalRequestDto - {}", responseDto, requestDto);
        return responseDto;
    }

    @Override
    public SearchResponseDto search(SearchRequestDto requestDto) {
        LOGGER.info("Retrieving a SearchResponseDto according to the SearchRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "SeachRequestDto must not be null");

        String searcherUsername = requestDto.getSearcherUsername();
        String keyWord = requestDto.getKeyWord();

        Optional<User> userOptionalWithUsername = userService.findByUsername(searcherUsername);
        if(userOptionalWithUsername.isEmpty()) {
            return new SearchResponseDto(List.of(String.format("No user found having an id of, %s", searcherUsername)));
        }

        List<UserDto> userDtoList = this.retrieveMultipleUsers(
                new UserListRetrievalRequestDto(searcherUsername, keyWord)
        ).getUserDtoList();

        List<ChatDto> chatDtoList = this.retrieveMultipleChats(
                new ChatListRetrievalRequestDto(searcherUsername, keyWord)
        ).getChatDtoList();

        SearchResponseDto responseDto = new SearchResponseDto(
                LocalDateTime.now(),
                searcherUsername,
                keyWord,
                userDtoList,
                chatDtoList
        );

        LOGGER.info("Successfully retrieved a SearchResponseDto - {}, according to the SearchRequestDto - {}", responseDto, requestDto);
        return responseDto;
    }
}

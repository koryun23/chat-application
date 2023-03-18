package com.chat.facade.impl.chat;

import com.chat.dto.plain.ChatDto;
import com.chat.dto.request.*;
import com.chat.dto.response.*;
import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;
import com.chat.entity.chat.type.ChatType;
import com.chat.entity.role.UserAppRole;
import com.chat.entity.role.type.UserAppRoleType;
import com.chat.entity.role.type.UserChatRoleType;
import com.chat.entity.user.User;
import com.chat.facade.core.chat.ChatFacade;
import com.chat.mapper.core.chat.ChatCreationRequestDtoToChatCreationParamsMapper;
import com.chat.service.core.chat.*;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChatFacadeImpl implements ChatFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatFacadeImpl.class);
    private final ChatService chatService;
    private final UserChatService userChatService;
    private final UserService userService;
    private final ChatCreationRequestDtoToChatCreationParamsMapper chatCreationRequestDtoToChatCreationParamsMapper;

    public ChatFacadeImpl(ChatService chatService, UserChatService userChatService, UserService userService, ChatCreationRequestDtoToChatCreationParamsMapper chatCreationRequestDtoToChatCreationParamsMapper) {
        this.chatService = chatService;
        this.userChatService = userChatService;
        this.userService = userService;
        this.chatCreationRequestDtoToChatCreationParamsMapper = chatCreationRequestDtoToChatCreationParamsMapper;
    }

    @Override
    public ChatCreationResponseDto createChat(ChatCreationRequestDto requestDto) {
        LOGGER.info("Creating a chat according to the chat creation request dto - {}", requestDto);
        Assert.notNull(requestDto, "Chat creation request dto must not be null");

        if (userWithUsernameDoesNotExist(requestDto.getCreatorUsername())) {
            return new ChatCreationResponseDto(List.of("No user is registered with a username of " + requestDto.getCreatorUsername()));
        }

        Chat chat = chatService.createChat(chatCreationRequestDtoToChatCreationParamsMapper.apply(requestDto));

        UserChatRoleType userChatRoleType = detectUserChatRole(requestDto.getChatType());

        UserChat userInChat = userChatService.createUserChat(new UserChatCreationParams(
                requestDto.getCreatorUsername(),
                chat.getId(),
                userChatRoleType
        ));

        ChatCreationResponseDto responseDto = new ChatCreationResponseDto(
                chat.getId(),
                chat.getName(),
                chat.getChatType(),
                chat.getCreatedAt(),
                userInChat.getUser().getUsername()
        );

        LOGGER.info("Successfully created a {} chat according to the chat creation request dto - {}, result - {}", chat.getChatType(), requestDto, responseDto);
        return responseDto;
    }

    @Override
    public ChatDeletionResponseDto deleteChat(ChatDeletionRequestDto requestDto) {
        LOGGER.info("Deleting a chat according to the chat deletion request dto - {}", requestDto);
        Assert.notNull(requestDto, "Chat deletion request dto must not be null");

        Long chatId = requestDto.getChatId();
        String deleterUsername = requestDto.getChatDeleterUsername();

        if (chatWithIdDoesNotExist(chatId)) {
            return new ChatDeletionResponseDto(List.of(String.format("Chat with a requested id(%s) does not exist, hence cannot be deleted", chatId)));
        }

        if (!isEligibleToUpdateChat(deleterUsername, chatId)) {
            return new ChatDeletionResponseDto(List.of(String.format("User '%s' is not eligible to delete the selected chat", deleterUsername)));
        }

        chatService.delete(chatId);
        ChatDeletionResponseDto responseDto = new ChatDeletionResponseDto(chatId);

        LOGGER.info("Successfully deleted a chat according to the Chat Deletion Request Dto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }

    @Override
    public ChatUpdateResponseDto updateChat(ChatUpdateRequestDto requestDto) {
        LOGGER.info("Updating a chat according to the Chat Update Request Dto - {}", requestDto);
        Assert.notNull(requestDto, "Chat Update Request Dto must not be null");

        Long chatId = requestDto.getChatId();
        String chatName = requestDto.getName();
        String updaterUsername = requestDto.getUpdaterUsername();

        if (chatWithIdDoesNotExist(chatId)) {
            return new ChatUpdateResponseDto(
                    List.of(String.format("Chat with an id of %s does not exist", chatId))
            );
        }

        if (!isEligibleToUpdateChat(updaterUsername, chatName)) {

        }
        Chat updatedChat = chatService.update(new ChatUpdateParams(
                chatId,
                chatName
        ));

        ChatUpdateResponseDto responseDto = new ChatUpdateResponseDto(
                updatedChat.getId(),
                updatedChat.getName()
        );

        LOGGER.info("Successfully updated a chat according to the Chat Update Request Dto - {}, result - {}", requestDto, responseDto);
        return responseDto;

    }

    @Override
    public UserChatUpdateResponseDto updateUserChat(UserChatUpdateRequestDto requestDto) {
        LOGGER.info("Updating UserChat according to the UserChatUpdateRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "UserChatUpdateRequestDto must not be null");

        Long userChatId = requestDto.getId();
        UserChatRoleType userChatRoleType = requestDto.getUserChatRoleType();
        String updaterUsername = requestDto.getUpdaterUsername();

        Optional<UserChat> userChatOptional = userChatWithId(userChatId);
        if (userChatOptional.isEmpty()) {
            return new UserChatUpdateResponseDto(
                    List.of(String.format("No User-In-Chat with an id of %s", userChatId))
            );
        }

        if (!isEligibleToUpdateChatUser(updaterUsername, userChatOptional.get())) {
            return new UserChatUpdateResponseDto(
                    List.of(String.format("The user '%s' is not eligible to make the requested change", updaterUsername))
            );
        }

        UserChat updatedUserChat = userChatService.updateUserChat(new UserChatUpdateParams(
                userChatId,
                userChatRoleType
        ));

        UserChatUpdateResponseDto responseDto = new UserChatUpdateResponseDto(
                updatedUserChat.getId(),
                updatedUserChat.getUserChatRoleType(),
                requestDto.getUpdaterUsername()
        );

        LOGGER.info("Successfully updated a UserChat according to the UserChatUpdateRequestDto - {}, result - {}", requestDto, responseDto);

        return responseDto;
    }

    @Override
    public UserChatCreationResponseDto createUserChat(UserChatCreationRequestDto requestDto) {
        LOGGER.info("Creating a user in chat according to the UserChatCreationRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "UserChatCreationRequestDto must not be null");

        String creatorUsername = requestDto.getCreatorUsername();
        String addedUserUsername = requestDto.getAddedUserUsername();
        Long chatId = requestDto.getChatId();
        UserChatRoleType userChatRoleType = requestDto.getUserChatRoleType();

        if (chatWithIdDoesNotExist(chatId)) {
            return new UserChatCreationResponseDto(
                    List.of(String.format("Chat with an id of %s does not exist.", chatId))
            );
        }
        if (userWithUsernameDoesNotExist(creatorUsername)) {
            return new UserChatCreationResponseDto(
                    List.of(String.format("User with username of %s does not exist.", creatorUsername))
            );
        }
        if (userWithUsernameDoesNotExist(addedUserUsername)) {
            return new UserChatCreationResponseDto(
                    List.of(String.format("User with username of %s does not exist", addedUserUsername))
            );
        }
        if (!isEligibleToUpdateChat(creatorUsername, chatId)) {
            return new UserChatCreationResponseDto(
                    List.of(String.format("User '%s' is not eligible to add a user to chat", creatorUsername))
            );
        }

        if (userChatRoleType == UserChatRoleType.CHAT_OWNER) {
            return new UserChatCreationResponseDto(
                    List.of(String.format("Cannot make this user chat owner"))
            );
        }

        UserChat userChat = userChatService.createUserChat(new UserChatCreationParams(
                addedUserUsername,
                chatId,
                userChatRoleType
        ));

        UserChatCreationResponseDto responseDto = new UserChatCreationResponseDto(
                creatorUsername,
                addedUserUsername,
                chatId,
                userChatRoleType,
                LocalDateTime.now()
        );

        LOGGER.info("Successfully added a user to the chat according to the UserChatCreationRequestDto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }

    @Override
    public UserChatRetrievalResponseDto retrieveUsersInChat(UserChatRetrievalRequestDto requestDto) {
        LOGGER.info("Retrieving all users in chat according to the UserChatRetrievalRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "UserChatRetrievalRequestDto must not be null");

        String requestingUsername = requestDto.getRequestingUsername();
        Long chatId = requestDto.getChatId();

        if (chatWithIdDoesNotExist(chatId)) {
            return new UserChatRetrievalResponseDto(
                    List.of(String.format("Chat with an id of %s does not exist", chatId))
            );
        }

        if (userWithUsernameDoesNotExist(requestingUsername)) {
            return new UserChatRetrievalResponseDto(
                    List.of(String.format("User '%s' does not exist", requestingUsername))
            );
        }

        if (userIsNotMemberOfChat(requestingUsername, chatId) && !userHasAppRole(requestingUsername, UserAppRoleType.APP_SUPERADMIN)) {
            return new UserChatRetrievalResponseDto(
                    List.of(String.format("User '%s' is not eligible to watch the list of users", requestingUsername))
            );
        }

        List<UserChat> allUsersInChatWithId = userChatService.getAll(chatId);
        UserChatRetrievalResponseDto responseDto = new UserChatRetrievalResponseDto(
                requestingUsername,
                chatId,
                allUsersInChatWithId.stream().map(user -> new UserChatCreationParams(user.getUser().getUsername(), chatId, user.getUserChatRoleType())).collect(Collectors.toList())
        );

        LOGGER.info("Successfully retrieved all users in chat according to the UserChatRetrievalRequestDto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }

    private boolean userWithUsernameDoesNotExist(String username) {
        return userService.findByUsername(username).isEmpty();
    }

    private boolean userWithIdDoesNotExist(Long id) {
        return userService.findById(id).isEmpty();
    }

    private UserChatRoleType detectUserChatRole(ChatType chatType) {
        return chatType == ChatType.GROUP ? UserChatRoleType.CHAT_OWNER : UserChatRoleType.CHAT_ADMIN;
    }

    private boolean chatWithIdDoesNotExist(Long id) {
        return chatService.findById(id).isEmpty();
    }

    private boolean userChatWithIdDoesNotExist(Long id) {
        return userChatService.findById(id).isEmpty();
    }

    private Optional<UserChat> userChatWithId(Long id) {
        return userChatService.findById(id);
    }


    private List<UserChat> usersInChatWithId(Long chatId) {
        return chatService.getById(chatId).getUsersInChat();
    }

    private boolean isEligibleToUpdateChatUser(String username, UserChat userChat) {
        return userHasAppRole(username, UserAppRoleType.APP_SUPERADMIN) || userIsAdminOrOwner(username, userChat.getChat().getUsersInChat());
    }

    private boolean isEligibleToUpdateChat(String username, String chatName) {
        return userHasAppRole(username, UserAppRoleType.APP_SUPERADMIN) || userIsAdminOrOwner(username, chatService.getByName(chatName).getUsersInChat());
    }

    private boolean isEligibleToUpdateChat(String username, Long id) {
        return userHasAppRole(username, UserAppRoleType.APP_SUPERADMIN) || userIsAdminOrOwner(username, chatService.getById(id).getUsersInChat());
    }

    private boolean userHasAppRole(String username, UserAppRoleType userAppRoleType) {
        return userService.getByUsername(username).getUserAppRoles().stream().map(UserAppRole::getUserAppRoleType).collect(Collectors.toList()).contains(userAppRoleType);
    }

    private boolean userIsAdminOrOwner(String username, List<UserChat> usersInChat) {
        for (UserChat currentUserChat : usersInChat) {
            User currentUser = currentUserChat.getUser();
            if (currentUser.getUsername().equals(username)) {
                UserChatRoleType currentUserChatRoleType = currentUserChat.getUserChatRoleType();
                return currentUserChatRoleType == UserChatRoleType.CHAT_ADMIN || currentUserChatRoleType == UserChatRoleType.CHAT_OWNER;
            }
        }
        return false;
    }

    private boolean userIsNotMemberOfChat(String username, Long chatId) {
        return !chatService.getById(chatId).getUsersInChat().stream().map(UserChat::getUser).map(User::getUsername).collect(Collectors.toList()).contains(username);
    }
}

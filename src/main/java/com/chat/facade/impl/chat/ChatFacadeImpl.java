package com.chat.facade.impl.chat;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.dto.request.ChatDeletionRequestDto;
import com.chat.dto.request.ChatUpdateRequestDto;
import com.chat.dto.response.ChatCreationResponseDto;
import com.chat.dto.response.ChatDeletionResponseDto;
import com.chat.dto.response.ChatUpdateResponseDto;
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
import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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
                userService.getByUsername(requestDto.getCreatorUsername()).getId(),
                chat.getId(),
                userChatRoleType
        ));

        ChatCreationResponseDto responseDto = new ChatCreationResponseDto(
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

        if(chatWithIdDoesNotExist(chatId)) {
            return new ChatDeletionResponseDto(List.of(String.format("Chat with a requested id(%s) does not exist, hence cannot be deleted", chatId)));
        }

        if(chatDeleterIsEligibleToDeleteChatWithId(deleterUsername, chatId)) {
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

        if(chatWithIdDoesNotExist(chatId)) {
            return new ChatUpdateResponseDto(
                    List.of(String.format("Chat with an id of %s does not exist", chatId))
            );
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

    private boolean userWithUsernameDoesNotExist(String username) {
        return userService.findByUsername(username).isEmpty();
    }

    private UserChatRoleType detectUserChatRole(ChatType chatType) {
        return chatType == ChatType.GROUP ? UserChatRoleType.CHAT_OWNER : UserChatRoleType.CHAT_USER;
    }

    private boolean chatWithIdDoesNotExist(Long id) {
        return chatService.findById(id).isEmpty();
    }

    private boolean chatDeleterIsEligibleToDeleteChatWithId(String deleterUsername, Long chatId) {
        for(UserChat userChat : usersInChatWithId(chatId)) {
            User user = userChat.getUser();
            if((user.getUsername().equals(deleterUsername) && userChat.getUserChatRoleType() == UserChatRoleType.CHAT_OWNER) ||
                    user.getUserAppRoles().stream().map(UserAppRole::getUserAppRoleType).collect(Collectors.toList()).contains(UserAppRoleType.APP_SUPERADMIN)) {
                return true;
            }
        }
        return false;
    }

    private List<UserChat> usersInChatWithId(Long chatId) {
        return chatService.getById(chatId).getUsersInChat();
    }


}

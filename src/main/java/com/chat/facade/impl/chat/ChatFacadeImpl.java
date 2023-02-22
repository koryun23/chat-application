package com.chat.facade.impl.chat;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.dto.response.ChatCreationResponseDto;
import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;
import com.chat.entity.chat.type.ChatType;
import com.chat.entity.role.type.UserChatRoleType;
import com.chat.facade.core.chat.ChatFacade;
import com.chat.mapper.core.chat.ChatCreationRequestDtoToChatCreationParamsMapper;
import com.chat.service.core.chat.ChatCreationParams;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.chat.UserChatCreationParams;
import com.chat.service.core.chat.UserChatService;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

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

        List<String> errors = new LinkedList<>();

        if (userWithUsernameDoesNotExist(requestDto.getCreatorUsername())) {
            errors.add("No user is registered with a username of " + requestDto.getCreatorUsername());
        }

        if (errorsFound(errors)) {
            return new ChatCreationResponseDto(errors);
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

    private boolean userWithUsernameDoesNotExist(String username) {
        return userService.findByUsername(username).isEmpty();
    }

    private boolean errorsFound(List<String> errors) {
        return errors.size() != 0;
    }

    private UserChatRoleType detectUserChatRole(ChatType chatType) {
        return chatType == ChatType.GROUP ? UserChatRoleType.CHAT_OWNER : UserChatRoleType.CHAT_USER;
    }
}

package com.chat.service.impl.chat;

import com.chat.entity.chat.UserChat;
import com.chat.exceptions.ChatNotFoundException;
import com.chat.repository.UserChatRepository;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.chat.UserChatCreationParams;
import com.chat.service.core.chat.UserChatService;
import com.chat.service.core.user.UserService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserChatServiceImpl implements UserChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserChatServiceImpl.class);
    private final UserChatRepository userChatRepository;
    private final UserService userService;
    private final ChatService chatService;

    public UserChatServiceImpl(UserChatRepository userChatRepository, UserService userService, ChatService chatService) {
        this.userChatRepository = userChatRepository;
        this.userService = userService;
        this.chatService = chatService;
    }

    @Override
    public UserChat createUserChat(UserChatCreationParams params) {
        LOGGER.info("Adding a user in chat according to user in chat creation params - {}", params);
        Assert.notNull(params, "User in chat creation params must not be null");
        UserChat savedUserChat = userChatRepository.save(new UserChat(
                userService.getById(params.getUserId()),
                chatService.getById(params.getChatId()),
                params.getUserChatRoleType()
        ));
        LOGGER.info("Successfully added a user in chat according to user in chat creation params - {}, result - {}", params, savedUserChat);
        return savedUserChat;
    }
}

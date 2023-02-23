package com.chat.service.impl.chat;

import com.chat.entity.chat.UserChat;
import com.chat.exceptions.ChatNotFoundException;
import com.chat.exceptions.UserChatNotFoundException;
import com.chat.repository.UserChatRepository;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.chat.UserChatCreationParams;
import com.chat.service.core.chat.UserChatService;
import com.chat.service.core.chat.UserChatUpdateParams;
import com.chat.service.core.user.UserService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public UserChat updateUserChat(UserChatUpdateParams params) {
        LOGGER.info("Updating a user in chat according to the User Chat Update params - {}", params);
        Assert.notNull(params, "User Chat Update params must not be null");

        UserChat existingUserChat = getById(params.getUserChatId());
        UserChat userChat = new UserChat(
                existingUserChat.getUser(),
                existingUserChat.getChat(),
                params.getUserChatRoleType()
        );
        userChat.setId(existingUserChat.getId());

        UserChat updatedUserChat = userChatRepository.update(userChat);

        LOGGER.info("Successfully updated an existing UserChat - {}, according to the UserChatUpdateParams - {}, result - {}", existingUserChat, params, updatedUserChat);

        return updatedUserChat;

    }

    @Override
    public UserChat getById(Long id) {
        LOGGER.info("Retrieving a UserChat with an id of {}", id);
        Assert.notNull(id, "UserChat id must not be null");
        UserChat userChat = userChatRepository.findById(id).orElseThrow(() -> new UserChatNotFoundException(id));
        LOGGER.info("Successfully retrieved a UserChat with an id of {}, result - {}", id, userChat);
        return userChat;

    }

    @Override
    public Optional<UserChat> findById(Long id) {
        LOGGER.info("Retrieving an optional UserChat with an id of {}", id);
        Assert.notNull(id, "UserChat id must not be null");
        Optional<UserChat> userChatOptional = userChatRepository.findById(id);
        LOGGER.info("Successfully retrieved an optional UserChat with an id of {}, result - {}", id, userChatOptional);
        return userChatOptional;
    }
}

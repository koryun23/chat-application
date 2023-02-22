package com.chat.service.impl.message.persist;

import com.chat.entity.message.UserMessage;
import com.chat.repository.UserMessageRepository;
import com.chat.service.core.message.persist.MessagePersistenceService;
import com.chat.service.core.message.persist.UserMessageCreationParams;
import com.chat.service.core.message.persist.UserMessagePersistenceService;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UserMessagePersistenceServiceImpl implements UserMessagePersistenceService {

    private final UserMessageRepository userMessageRepository;
    private final UserService userService;
    private final MessagePersistenceService messagePersistenceService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMessagePersistenceServiceImpl.class);

    public UserMessagePersistenceServiceImpl(UserMessageRepository userMessageRepository, UserService userService, MessagePersistenceService messagePersistenceService) {
        this.userMessageRepository = userMessageRepository;
        this.userService = userService;
        this.messagePersistenceService = messagePersistenceService;
    }

    @Transactional
    @Override
    public UserMessage create(UserMessageCreationParams params) {
        LOGGER.info("Creating a User Message according to the User Message Creation Params - {}", params);
        Assert.notNull(params, "User Message Creation Params must not be null");
        UserMessage userMessage = userMessageRepository.save(new UserMessage(
                userService.getByUsername(params.getSentTo()),
                messagePersistenceService.getById(params.getMessageId()),
                params.getMessageStatusType()
        ));
        LOGGER.info("Successfully created a user message according to the user message creation params - {}, result - {}", params, userMessage);
        return userMessage;
    }
}

package com.chat.service.impl.message.persist;

import com.chat.entity.message.Message;
import com.chat.exceptions.MessageNotFoundException;
import com.chat.mapper.core.message.MessageCreationParamsToMessageMapper;
import com.chat.repository.MessageRepository;
import com.chat.service.core.message.persist.MessageCreationParams;
import com.chat.service.core.message.persist.MessagePersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class MessagePersistenceServiceImpl implements MessagePersistenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagePersistenceServiceImpl.class);
    private final MessageRepository messageRepository;
    private final MessageCreationParamsToMessageMapper messageCreationParamsToMessageMapper;

    public MessagePersistenceServiceImpl(MessageRepository messageRepository, MessageCreationParamsToMessageMapper messageCreationParamsToMessageMapper) {
        this.messageRepository = messageRepository;
        this.messageCreationParamsToMessageMapper = messageCreationParamsToMessageMapper;
    }

    @Transactional
    @Override
    public Message create(MessageCreationParams params) {
        LOGGER.info("Creating a message according to the Message Creation Params - {}", params);
        Assert.notNull(params, "Message Creation Params must not be null");
        Message message = messageRepository.save(messageCreationParamsToMessageMapper.apply(params));
        LOGGER.info("Successfully created a message according to the Message Creation Params - {}, result - {}", params, message);
        return message;
    }

    @Transactional
    @Override
    public Message getById(Long id) {
        LOGGER.info("Retrieving a message having an id of {}", id);
        Assert.notNull(id, "Message id must not be null when trying to retrieve a message");
        Message message = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        LOGGER.info("Successfully retrieved a message having an id of {}, result - {}", id, message);
        return message;
    }
}

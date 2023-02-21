package com.chat.service.impl.message.persist;

import com.chat.entity.message.ChatMessage;
import com.chat.repository.ChatMessageRepository;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.message.persist.ChatMessageCreationParams;
import com.chat.service.core.message.persist.ChatMessagePersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ChatMessagePersistenceServiceImpl implements ChatMessagePersistenceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessagePersistenceServiceImpl.class);
    private final ChatMessageRepository chatMessageRepository;
    private final ChatService chatService;
    private final MessagePersistenceServiceImpl messagePersistenceService;

    public ChatMessagePersistenceServiceImpl(ChatMessageRepository chatMessageRepository, ChatService chatService, MessagePersistenceServiceImpl messagePersistenceService) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatService = chatService;
        this.messagePersistenceService = messagePersistenceService;
    }

    @Override
    public ChatMessage create(ChatMessageCreationParams params) {
        LOGGER.info("Creating a chat message according to the Chat Message Creation Params - {}", params);
        Assert.notNull(params, "Chat Message Creation Params must not be null");
        ChatMessage chatMessage = chatMessageRepository.save(new ChatMessage(
                chatService.getById(params.getChatId()),
                messagePersistenceService.getById(params.getChatId())
        ));
        LOGGER.info("Successfully created a Chat Message according to the Chat Message Creation Params - {}, result - {}", params, chatMessage);
        return chatMessage;
    }
}

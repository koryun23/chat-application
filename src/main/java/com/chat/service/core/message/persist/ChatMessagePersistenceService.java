package com.chat.service.core.message.persist;

import com.chat.entity.message.ChatMessage;

public interface ChatMessagePersistenceService {

    ChatMessage create(ChatMessageCreationParams params);
}

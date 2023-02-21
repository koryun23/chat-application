package com.chat.service.core.message.persist;

import com.chat.entity.message.Message;

public interface MessagePersistenceService {

    Message create(MessageCreationParams params);

    Message getById(Long id);
}

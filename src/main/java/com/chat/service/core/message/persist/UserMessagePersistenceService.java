package com.chat.service.core.message.persist;

import com.chat.entity.message.UserMessage;

public interface UserMessagePersistenceService {

    UserMessage create(UserMessageCreationParams params);
}

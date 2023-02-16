package com.chat.service.core.message.sender;

import com.chat.service.impl.message.sender.MessageCreationParams;

public interface MessageSenderService {
    void sendMessage(MessageCreationParams messageCreationParams);
}

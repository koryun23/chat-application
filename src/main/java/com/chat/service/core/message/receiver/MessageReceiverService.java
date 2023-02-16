package com.chat.service.core.message.receiver;

import com.chat.service.impl.message.sender.MessageCreationParams;

public interface MessageReceiverService {
    void receiveMessage(MessageCreationParams messageCreationParams);
}

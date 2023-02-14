package com.chat.service.core.message.sender;

import com.chat.service.impl.message.sender.Message;

public interface MessageSenderService {
    void sendMessage(Message message);
}

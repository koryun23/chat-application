package com.chat.service.core.message.receiver;

import com.chat.service.impl.message.sender.Message;

public interface MessageReceiverService {
    void receiveMessage(Message message);
}

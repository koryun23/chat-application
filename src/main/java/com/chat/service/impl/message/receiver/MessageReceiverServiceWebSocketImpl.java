package com.chat.service.impl.message.receiver;

import com.chat.service.core.message.receiver.MessageReceiverService;
import com.chat.service.impl.message.sender.PrivateMessageCreationParams;
import com.chat.service.impl.message.sender.PublicMessageCreationParams;
import org.springframework.transaction.annotation.Transactional;

public class MessageReceiverServiceWebSocketImpl implements MessageReceiverService {

    @Transactional
    @Override
    public void receivePrivateMessage(PrivateMessageCreationParams messageCreationParams) {

    }

    @Transactional
    @Override
    public void receivePublicMessage(PublicMessageCreationParams messageCreationParams) {

    }
}

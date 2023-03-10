package com.chat.service.impl.message.receiver;

import com.chat.service.core.message.receiver.MessageReceiverService;
import com.chat.service.core.message.sender.PrivateMessageCreationParams;
import com.chat.service.core.message.sender.PublicMessageCreationParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
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

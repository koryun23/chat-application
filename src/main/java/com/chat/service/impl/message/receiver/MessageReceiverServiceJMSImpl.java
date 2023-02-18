package com.chat.service.impl.message.receiver;

import com.chat.service.core.message.receiver.MessageReceiverService;
import com.chat.service.impl.message.sender.PrivateMessageCreationParams;
import com.chat.service.impl.message.sender.PublicMessageCreationParams;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiverServiceJMSImpl implements MessageReceiverService {

    @JmsListener(destination = "user", containerFactory = "myFactory")
    @Override
    public void receivePrivateMessage(PrivateMessageCreationParams privateMessageCreationParams) {
        System.out.println("Received message - " + privateMessageCreationParams.getMessage());
    }

    @JmsListener(destination = "chatroom", containerFactory = "myFactory")
    @Override
    public void receivePublicMessage(PublicMessageCreationParams publicMessageCreationParams) {
        System.out.println("Received message - " + publicMessageCreationParams.getMessage());
    }
}
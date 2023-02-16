package com.chat.service.impl.message.receiver;

import com.chat.service.core.message.receiver.MessageReceiverService;
import com.chat.service.impl.message.sender.MessageCreationParams;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiverServiceImpl implements MessageReceiverService {

    @Override
    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(MessageCreationParams messageCreationParams){
        System.out.println("Received message - " + messageCreationParams.getMessage());
    }
}
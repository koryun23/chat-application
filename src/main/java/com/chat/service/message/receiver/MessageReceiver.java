package com.chat.service.message.receiver;

import com.chat.service.message.sender.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receieveMessage(Message message){
        System.out.println("Received message - " + message.getMessage());
    }
}

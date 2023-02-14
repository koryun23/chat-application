package com.chat.service.message.sender;

import com.chat.service.message.sender.Message;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private JmsTemplate jmsTemplate;

    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    // this method should store the sent message and all its details in the database
    public void sendMessage(Message message) {
        jmsTemplate.convertAndSend("mailbox", message);
    }
}

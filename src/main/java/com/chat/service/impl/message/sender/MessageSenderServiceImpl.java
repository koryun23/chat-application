package com.chat.service.impl.message.sender;

import com.chat.service.core.message.sender.MessageSenderService;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.Destination;
import java.net.URI;

@Component
public class MessageSenderServiceImpl implements MessageSenderService {

    private JmsTemplate jmsTemplate;

    public MessageSenderServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    // this method should store the sent message and all its details in the database
    public void sendMessage(MessageCreationParams messageCreationParams) {
        jmsTemplate.convertAndSend(messageCreationParams);
    }
}

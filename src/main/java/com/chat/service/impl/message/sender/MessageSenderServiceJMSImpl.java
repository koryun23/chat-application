package com.chat.service.impl.message.sender;

import com.chat.service.core.message.sender.MessageSenderService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;


@Service
public class MessageSenderServiceJMSImpl implements MessageSenderService {

    private JmsTemplate jmsTemplate;

    public MessageSenderServiceJMSImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    // this method should store the sent message and all its details in the database
    @Override
    public void sendPrivateMessage(PrivateMessageCreationParams privateMessageCreationParams) {
        jmsTemplate.convertAndSend(privateMessageCreationParams);
    }

    @Override
    public void sendPublicMessage(PublicMessageCreationParams publicMessageCreationParams) {
        jmsTemplate.convertAndSend(publicMessageCreationParams);
    }


}

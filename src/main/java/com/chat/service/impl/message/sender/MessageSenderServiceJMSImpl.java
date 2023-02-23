package com.chat.service.impl.message.sender;

import com.chat.service.core.message.sender.MessageSenderService;
import com.chat.service.core.message.sender.PrivateMessageCreationParams;
import com.chat.service.core.message.sender.PublicMessageCreationParams;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MessageSenderServiceJMSImpl implements MessageSenderService {

    private final JmsTemplate jmsTemplate;

    public MessageSenderServiceJMSImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    // this method should store the sent message and all its details in the database
    @Transactional
    @Override
    public void sendPrivateMessage(PrivateMessageCreationParams privateMessageCreationParams) {
        jmsTemplate.convertAndSend(privateMessageCreationParams);
    }

    @Transactional
    @Override
    public void sendPublicMessage(PublicMessageCreationParams publicMessageCreationParams) {
        jmsTemplate.convertAndSend(publicMessageCreationParams);
    }


}

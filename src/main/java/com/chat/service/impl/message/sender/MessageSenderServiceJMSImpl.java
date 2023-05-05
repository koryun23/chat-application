package com.chat.service.impl.message.sender;

import com.chat.dto.plain.MessageDto;
import com.chat.service.core.message.sender.MessageSenderService;
import com.chat.service.core.message.sender.PrivateMessageCreationParams;
import com.chat.service.core.message.sender.PublicMessageCreationParams;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class MessageSenderServiceJMSImpl implements MessageSenderService {

    private final JmsTemplate jmsTemplate;

    public MessageSenderServiceJMSImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    // this method should store the sent message and all its details in the database
    @Transactional
    @Override
    public void sendPrivateMessage(MessageDto messageDto) {
        jmsTemplate.convertAndSend(messageDto);
    }

    @Transactional
    @Override
    public void sendPublicMessage(PublicMessageCreationParams publicMessageCreationParams) {
        jmsTemplate.convertAndSend(publicMessageCreationParams);
    }


}

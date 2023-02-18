package com.chat.service.impl.message.sender;

import com.chat.service.core.message.sender.MessageSenderService;
import io.jsonwebtoken.lang.Assert;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderServiceWebSocketImpl implements MessageSenderService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public MessageSenderServiceWebSocketImpl(SimpMessagingTemplate simpMessagingTemplate) {
        Assert.notNull(simpMessagingTemplate, "Simple Messaging Template must not be null");
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void sendPrivateMessage(PrivateMessageCreationParams messageCreationParams) {
        simpMessagingTemplate.convertAndSendToUser(messageCreationParams.getSentTo(), "/private", messageCreationParams);
    }

    @Override
    public void sendPublicMessage(PublicMessageCreationParams publicMessageCreationParams) {

    }
}

package com.chat.service.impl.message.sender;

import com.chat.repository.ChatMessageRepository;
import com.chat.repository.MessageRepository;
import com.chat.repository.UserMessageRepository;
import com.chat.service.core.message.sender.MessageSenderService;
import com.chat.service.core.message.sender.PrivateMessageCreationParams;
import com.chat.service.core.message.sender.PublicMessageCreationParams;
import io.jsonwebtoken.lang.Assert;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageSenderServiceWebSocketImpl implements MessageSenderService {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public MessageSenderServiceWebSocketImpl(SimpMessagingTemplate simpMessagingTemplate) {
        Assert.notNull(simpMessagingTemplate, "Simple Messaging Template must not be null");
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Transactional
    @Override
    public void sendPrivateMessage(PrivateMessageCreationParams messageCreationParams) {
        simpMessagingTemplate.convertAndSendToUser(messageCreationParams.getSentTo(), "/private", messageCreationParams);
    }

    @Transactional
    @Override
    public void sendPublicMessage(PublicMessageCreationParams publicMessageCreationParams) {

    }
}

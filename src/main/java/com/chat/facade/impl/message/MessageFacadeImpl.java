package com.chat.facade.impl.message;

import com.chat.dto.request.SendNotificationRequestDto;
import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.dto.request.SendPublicMessageRequestDto;
import com.chat.dto.response.SendNotificationResponseDto;
import com.chat.dto.response.SendPrivateMessageResponseDto;
import com.chat.dto.response.SendPublicMessageResponseDto;
import com.chat.entity.chat.Chat;
import com.chat.entity.message.Message;
import com.chat.entity.message.type.MessageStatusType;
import com.chat.facade.core.message.MessageFacade;
import com.chat.handler.HttpServletRequestHandler;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.message.persist.*;
import com.chat.service.core.message.receiver.MessageReceiverService;
import com.chat.service.core.message.sender.MessageSenderService;
import com.chat.service.core.message.sender.PrivateMessageCreationParams;
import com.chat.service.core.message.sender.PublicMessageCreationParams;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class MessageFacadeImpl implements MessageFacade {

    private final MessagePersistenceService messagePersistenceService;
    private final ChatMessagePersistenceService chatMessagePersistenceService;
    private final UserMessagePersistenceService userMessagePersistenceService;
    private final MessageSenderService messageSenderService;
    private final MessageReceiverService messageReceiverService;
    private final ChatService chatService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JwtService jwtService;
    private final HttpServletRequestHandler httpServletRequestHandler;
    private final Logger LOGGER = LoggerFactory.getLogger(MessageFacadeImpl.class);

    public MessageFacadeImpl(MessagePersistenceService messagePersistenceService,
                             ChatMessagePersistenceService chatMessagePersistenceService,
                             UserMessagePersistenceService userMessagePersistenceService, MessageSenderService messageSenderService,
                             MessageReceiverService messageReceiverService,
                             ChatService chatService,
                             SimpMessagingTemplate simpMessagingTemplate,
                             JwtService jwtService,
                             HttpServletRequestHandler httpServletRequestHandler) {
        Assert.notNull(messagePersistenceService, "Message Persistence Service must not be null");
        Assert.notNull(chatMessagePersistenceService, "ChatMessagePersistenceService must not be null");
        Assert.notNull(userMessagePersistenceService, "UserMessagePersistenceService must not be null");
        Assert.notNull(messageReceiverService, "Message Receiver Service must not be null");
        Assert.notNull(messageSenderService, "Message Sender Service must not be null");
        Assert.notNull(chatService, "ChatService must not be null");
        Assert.notNull(simpMessagingTemplate, "Simple Messaging Template must not be null");
        Assert.notNull(jwtService, "Jwt Service must not be null");
        Assert.notNull(httpServletRequestHandler, "Http servlet request handler must not be null");
        this.messagePersistenceService = messagePersistenceService;
        this.chatMessagePersistenceService = chatMessagePersistenceService;
        this.userMessagePersistenceService = userMessagePersistenceService;
        this.messageSenderService = messageSenderService;
        this.messageReceiverService = messageReceiverService;
        this.chatService = chatService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.jwtService = jwtService;
        this.httpServletRequestHandler = httpServletRequestHandler;
    }

    @Override
    public SendPrivateMessageResponseDto sendPrivateMessage(SendPrivateMessageRequestDto requestDto) {
        LOGGER.info("Sending a private message according to Send Private Message Request dto - {}", requestDto);
        Assert.notNull(requestDto, "Send Private Message Request Dto must not be null");

        messageSenderService.sendPrivateMessage(new PrivateMessageCreationParams(
                requestDto.getMessage(),
                requestDto.getSentTo(),
                requestDto.getSentBy(),
                LocalDateTime.now()
        ));

        Message message = messagePersistenceService.create(new MessageCreationParams(
                requestDto.getMessage(),
                requestDto.getSentBy(),
                LocalDateTime.now()
        ));

        Optional<Chat> chatOptional = chatService.findByName(requestDto.getSentBy() + " - " + requestDto.getSentTo());
        Chat chat = chatOptional.orElseGet(() -> chatService.getByName(requestDto.getSentTo() + " - " + requestDto.getSentBy()));
        System.out.println(chat.getName());
        chatMessagePersistenceService.create(new ChatMessageCreationParams(
                chat.getId(),
                message.getId()
        ));

        userMessagePersistenceService.create(new UserMessageCreationParams(
                requestDto.getSentTo(),
                message.getId(),
                MessageStatusType.UNSEEN
        ));

        SendPrivateMessageResponseDto responseDto = new SendPrivateMessageResponseDto(
                requestDto.getMessage(),
                requestDto.getSentTo(),
                requestDto.getSentBy(),
                LocalDateTime.now()
        );

        LOGGER.info("Successfully sent a private message according to Send Private Message Request Dto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }

    @Override
    public SendPublicMessageResponseDto sendPublicMessage(SendPublicMessageRequestDto requestDto) {
        LOGGER.info("Sending a public message according to Send Public Message Request dto - {}", requestDto);
        Assert.notNull(requestDto, "Send Public Message Request Dto must not be null");

        messageSenderService.sendPublicMessage(new PublicMessageCreationParams(
                requestDto.getMessage(),
                requestDto.getChat(),
                requestDto.getSentBy()
        ));

        messagePersistenceService.create(new MessageCreationParams(
                requestDto.getMessage(),
                requestDto.getSentBy(),
                LocalDateTime.now()
        ));


        SendPublicMessageResponseDto responseDto = new SendPublicMessageResponseDto(
                requestDto.getMessage(),
                requestDto.getSentBy(),
                requestDto.getChat(),
                LocalDateTime.now()
        );

        LOGGER.info("Successfully sent a public message according to Send Public Message Request Dto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }

    @Override
    public SendNotificationResponseDto sendNotification(SendNotificationRequestDto requestDto) {
        LOGGER.info("Sending a notification according to the SendNotificationRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "SendNotificationRequestDto must not be null");
        simpMessagingTemplate.convertAndSend(
                String.format("/user/%s", requestDto.getSentTo()),
                requestDto
        );
        SendNotificationResponseDto responseDto = new SendNotificationResponseDto(
                requestDto.getNotificationType(),
                requestDto.getSentTo(),
                LocalDateTime.now()
        );

        // not persisting the notification

        LOGGER.info("Successfully sent a notification according to the SendNotificationRequestDto - {}, response - {}", requestDto, responseDto);
        return responseDto;
    }
}

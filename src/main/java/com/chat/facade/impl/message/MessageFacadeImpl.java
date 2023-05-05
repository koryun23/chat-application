package com.chat.facade.impl.message;

import com.chat.dto.plain.MessageDto;
import com.chat.dto.request.MessagesInChatListRetrievalRequestDto;
import com.chat.dto.request.SendNotificationRequestDto;
import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.dto.request.SendPublicMessageRequestDto;
import com.chat.dto.response.MessagesInChatListRetrievalResponseDto;
import com.chat.dto.response.SendNotificationResponseDto;
import com.chat.dto.response.SendPrivateMessageResponseDto;
import com.chat.dto.response.SendPublicMessageResponseDto;
import com.chat.entity.chat.Chat;
import com.chat.entity.message.ChatMessage;
import com.chat.entity.message.Message;
import com.chat.entity.message.type.MessageStatusType;
import com.chat.facade.core.message.MessageFacade;
import com.chat.handler.HttpServletRequestHandler;
import com.chat.helper.Utils;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.message.persist.*;
import com.chat.service.core.message.receiver.MessageReceiverService;
import com.chat.service.core.message.sender.MessageSenderService;
import com.chat.service.core.message.sender.PrivateMessageCreationParams;
import com.chat.service.core.message.sender.PublicMessageCreationParams;
import com.chat.service.core.user.UserService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MessageFacadeImpl implements MessageFacade {

    private final MessagePersistenceService messagePersistenceService;
    private final ChatMessagePersistenceService chatMessagePersistenceService;
    private final UserMessagePersistenceService userMessagePersistenceService;
    private final MessageSenderService messageSenderService;
    private final MessageReceiverService messageReceiverService;
    private final ChatService chatService;
    private final UserService userService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JwtService jwtService;
    private final HttpServletRequestHandler httpServletRequestHandler;
    private final Logger LOGGER = LoggerFactory.getLogger(MessageFacadeImpl.class);

    public MessageFacadeImpl(MessagePersistenceService messagePersistenceService,
                             ChatMessagePersistenceService chatMessagePersistenceService,
                             UserMessagePersistenceService userMessagePersistenceService, MessageSenderService messageSenderService,
                             MessageReceiverService messageReceiverService,
                             ChatService chatService,
                             UserService userService, SimpMessagingTemplate simpMessagingTemplate,
                             JwtService jwtService,
                             HttpServletRequestHandler httpServletRequestHandler) {
        Assert.notNull(messagePersistenceService, "Message Persistence Service must not be null");
        Assert.notNull(chatMessagePersistenceService, "ChatMessagePersistenceService must not be null");
        Assert.notNull(userMessagePersistenceService, "UserMessagePersistenceService must not be null");
        Assert.notNull(messageReceiverService, "Message Receiver Service must not be null");
        Assert.notNull(messageSenderService, "Message Sender Service must not be null");
        Assert.notNull(chatService, "ChatService must not be null");
        Assert.notNull(userService, "UserService must not be null");
        Assert.notNull(simpMessagingTemplate, "Simple Messaging Template must not be null");
        Assert.notNull(jwtService, "Jwt Service must not be null");
        Assert.notNull(httpServletRequestHandler, "Http servlet request handler must not be null");
        this.messagePersistenceService = messagePersistenceService;
        this.chatMessagePersistenceService = chatMessagePersistenceService;
        this.userMessagePersistenceService = userMessagePersistenceService;
        this.messageSenderService = messageSenderService;
        this.messageReceiverService = messageReceiverService;
        this.chatService = chatService;
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.jwtService = jwtService;
        this.httpServletRequestHandler = httpServletRequestHandler;
    }

    @Override
    public SendPrivateMessageResponseDto sendPrivateMessage(MessageDto messageDto) {
        LOGGER.info("Sending a private message - {}", messageDto);
        Assert.notNull(messageDto, "Message Dto must not be null");

        messageSenderService.sendPrivateMessage(messageDto);

        String sentBy = messageDto.getSentBy();
        String message = messageDto.getMessage();

        SendPrivateMessageResponseDto responseDto = new SendPrivateMessageResponseDto(
                message,
                Utils.getOtherUserFromPrivateChat(messageDto.getChatName(), sentBy),
                sentBy,
                LocalDateTime.now()
        );

        LOGGER.info("Successfully sent a private message - {}, result - {}", messageDto, responseDto);
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
    public SendPrivateMessageResponseDto savePrivateMessage(MessageDto messageDto) {
        // TODO: ADD VALIDATIONS

        LOGGER.info("Saving a private message - {}", messageDto);
        Assert.notNull(messageDto, "Message dto must not be null");

        Message message = messagePersistenceService.create(new MessageCreationParams(
                messageDto.getMessage(),
                messageDto.getSentBy(),
                LocalDateTime.now()
        ));

        Chat chat = chatService.getByName(messageDto.getChatName());
        String sentTo = Utils.getOtherUserFromPrivateChat(chat.getName(), messageDto.getSentBy());

        chatMessagePersistenceService.create(new ChatMessageCreationParams(
                chat.getId(),
                message.getId()
        ));

        userMessagePersistenceService.create(new UserMessageCreationParams(
                sentTo,
                message.getId(),
                MessageStatusType.UNSEEN
        ));

        SendPrivateMessageResponseDto responseDto = new SendPrivateMessageResponseDto(
                messageDto.getMessage(),
                sentTo,
                messageDto.getSentBy(),
                LocalDateTime.now()
        );

        LOGGER.info("Successfully saved a private message - {}, result - {}", messageDto, responseDto);
        return responseDto;
    }

    @Override
    public SendPublicMessageResponseDto savePublicMessage(SendPublicMessageRequestDto requestDto) {
        return null; // TODO: NEED TO IMPLEMENT THIS METHOD
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

    @Override
    public MessagesInChatListRetrievalResponseDto fetchMessagesInChat(MessagesInChatListRetrievalRequestDto requestDto) {
        LOGGER.info("Fetching messages in chat according to the MessagesInChatListRetrievalRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "MessageInChatListRetrievalRequestDto must not be null");

        Long chatId = requestDto.getChatId();
        String retrieverUsername = requestDto.getRetrieverUsername();

        if(userService.findByUsername(retrieverUsername).isEmpty()) {
            return new MessagesInChatListRetrievalResponseDto(
                    List.of(String.format("User %s not found", retrieverUsername))
            );
        }

        Optional<Chat> optionalChat = chatService.findById(chatId);
        if(optionalChat.isEmpty()) {
            return new MessagesInChatListRetrievalResponseDto(
                    List.of(String.format("No chat found having an id of %s", chatId))
            );
        }

        Chat chat = optionalChat.get();
        List<MessageDto> messageDtoList = chat.getChatMessages().stream()
                .map(ChatMessage::getMessage)
                .map(message -> new MessageDto(message.getBody(), message.getSentBy().getUsername(), chat.getName(), message.getSentAt()))
                .collect(Collectors.toList());

        MessagesInChatListRetrievalResponseDto responseDto = new MessagesInChatListRetrievalResponseDto(
                retrieverUsername,
                chat.getName(),
                messageDtoList,
                LocalDateTime.now()
        );

        LOGGER.info("Successfully retrieved a MessagesInChatListRetrievalResponseDto according to the MessagesInChatListRetrievalRequestDto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }
}

package com.chat.facade.impl.message;

import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.dto.request.SendPublicMessageRequestDto;
import com.chat.dto.response.SendPrivateMessageResponseDto;
import com.chat.dto.response.SendPublicMessageResponseDto;
import com.chat.facade.core.message.MessageFacade;
import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.message.receiver.MessageReceiverService;
import com.chat.service.core.message.sender.MessageSenderService;
import com.chat.service.core.message.sender.PrivateMessageCreationParams;
import com.chat.service.core.message.sender.PublicMessageCreationParams;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;

@Component
public class MessageFacadeImpl implements MessageFacade {

    private final MessageSenderService messageSenderService;
    private final MessageReceiverService messageReceiverService;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final JwtService jwtService;
    private final Logger LOGGER = LoggerFactory.getLogger(MessageFacadeImpl.class);

    public MessageFacadeImpl(MessageSenderService messageSenderService, MessageReceiverService messageReceiverService, SimpMessagingTemplate simpMessagingTemplate, JwtService jwtService) {
        Assert.notNull(messageReceiverService, "Message Receiver Service must not be null");
        Assert.notNull(messageSenderService, "Message Sender Service must not be null");
        Assert.notNull(simpMessagingTemplate, "Simple Messaging Template must not be null");
        Assert.notNull(jwtService, "Jwt Service must not be null");
        this.messageSenderService = messageSenderService;
        this.messageReceiverService = messageReceiverService;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.jwtService = jwtService;
    }

    public SendPrivateMessageResponseDto sendPrivateMessage(@RequestBody SendPrivateMessageRequestDto requestDto, HttpServletRequest request) {
        LOGGER.info("Sending a private message according to Send Private Message Request dto - {}", requestDto);
        Assert.notNull(requestDto, "Send Private Message Request Dto must not be null");

        requestDto.setSentBy(extractUsernameFromRequest(request));

        messageSenderService.sendPrivateMessage(new PrivateMessageCreationParams(
                requestDto.getMessage(),
                requestDto.getSentTo(),
                requestDto.getSentBy(),
                LocalDateTime.now()
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

    public SendPublicMessageResponseDto sendPublicMessage(SendPublicMessageRequestDto requestDto, HttpServletRequest request) {
        LOGGER.info("Sending a public message according to Send Public Message Request dto - {}", requestDto);
        Assert.notNull(requestDto, "Send Public Message Request Dto must not be null");

        requestDto.setSentBy(extractUsernameFromRequest(request));

        messageSenderService.sendPublicMessage(new PublicMessageCreationParams(
                requestDto.getMessage(),
                requestDto.getChat(),
                requestDto.getSentBy()
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

    private String extractUsernameFromRequest(HttpServletRequest request) {
        return jwtService.getUsername(request.getHeader("Authorization").substring(7));
    }
}

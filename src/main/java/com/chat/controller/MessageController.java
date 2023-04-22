package com.chat.controller;

import com.chat.dto.request.SendNotificationRequestDto;
import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.dto.request.SendPublicMessageRequestDto;
import com.chat.dto.response.SendNotificationResponseDto;
import com.chat.dto.response.SendPrivateMessageResponseDto;
import com.chat.dto.response.SendPublicMessageResponseDto;
import com.chat.facade.impl.message.MessageFacadeImpl;
import com.chat.handler.HttpServletRequestHandler;
import io.jsonwebtoken.lang.Assert;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping(path = "app", consumes = "application/json", produces = "application/json")
public class MessageController {

    private final MessageFacadeImpl messageFacade;
    private final HttpServletRequestHandler httpServletRequestHandler;

    public MessageController(MessageFacadeImpl messageFacade, SimpMessagingTemplate simpMessagingTemplate, HttpServletRequestHandler httpServletRequestHandler) {
        Assert.notNull(messageFacade, "Message Facade must not be null");
        Assert.notNull(httpServletRequestHandler, "Http servlet request handler must not be null");
        this.messageFacade = messageFacade;
        this.httpServletRequestHandler = httpServletRequestHandler;
    }

    @MessageMapping("/public-message") // /message
    //@SendTo("/chatroom/{chatName}")
    public SendPublicMessageResponseDto receivePublicMessage(@Payload SendPublicMessageRequestDto requestDto) {
        // when receiving a public message on the /message mapping, the message is being SENT to the /chatroom/{chatName} mapping
        // requestDto.setSentBy(httpServletRequestHandler.extractUsername(request));
        return messageFacade.sendPublicMessage(requestDto);
    }

    @MessageMapping("/private-message") // /private-message
    public SendPrivateMessageResponseDto receivePrivateMessage(@Payload SendPrivateMessageRequestDto requestDto) {
        // when receiving a private message on the mapping /private-message, it is being SENT to the appropriate user
        System.out.println("Entering the receivePrivateMessage method");
        System.out.println("SendPrivateMessageRequestDto " + requestDto);
        // requestDto.setSentBy(httpServletRequestHandler.extractUsername(request));
        return messageFacade.sendPrivateMessage(requestDto);
    }

    @MessageMapping("/notification")
    public SendNotificationResponseDto receiveNotification(@Payload SendNotificationRequestDto requestDto) {
        return messageFacade.sendNotification(requestDto);
    }
}

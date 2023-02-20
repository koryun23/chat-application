package com.chat.controller;

import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.dto.request.SendPublicMessageRequestDto;
import com.chat.dto.response.SendPrivateMessageResponseDto;
import com.chat.dto.response.SendPublicMessageResponseDto;
import com.chat.facade.impl.message.MessageFacadeImpl;
import io.jsonwebtoken.lang.Assert;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "app", consumes = "application/json", produces = "application/json")
public class MessageController {

    private final MessageFacadeImpl messageFacade;

    public MessageController(MessageFacadeImpl messageFacade, SimpMessagingTemplate simpMessagingTemplate) {
        Assert.notNull(messageFacade, "Message Facade must not be null");
        this.messageFacade = messageFacade;
    }

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public SendPublicMessageResponseDto receivePublicMessage(@Payload SendPublicMessageRequestDto requestDto) {
        // when receiving a public message on the /message mapping, the message is being SENT to the /chatroom/public mapping
        return messageFacade.sendPublicMessage(requestDto);
    }

    @MessageMapping("/private-message")
    public SendPrivateMessageResponseDto receivePrivateMessage(@Payload SendPrivateMessageRequestDto requestDto) {
        // when receiving a private message on the mapping /private-message, it is being SENT to the appropriate user
        return messageFacade.sendPrivateMessage(requestDto);
    }
}

package com.chat.controller;

import com.chat.dto.request.MessagesInChatListRetrievalRequestDto;
import com.chat.dto.request.SendNotificationRequestDto;
import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.dto.request.SendPublicMessageRequestDto;
import com.chat.dto.response.MessagesInChatListRetrievalResponseDto;
import com.chat.dto.response.SendNotificationResponseDto;
import com.chat.dto.response.SendPrivateMessageResponseDto;
import com.chat.dto.response.SendPublicMessageResponseDto;
import com.chat.facade.impl.message.MessageFacadeImpl;
import com.chat.handler.HttpServletRequestHandler;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/private-message/save")
    public SendPrivateMessageResponseDto savePrivateMessage(@RequestBody SendPrivateMessageRequestDto requestDto) {
        return messageFacade.savePrivateMessage(requestDto);
    }

    //TODO: ADD A savePublicMessage method with a post mapping of /public-message/save

    @MessageMapping("/notification")
    public SendNotificationResponseDto receiveNotification(@Payload SendNotificationRequestDto requestDto) {
        return messageFacade.sendNotification(requestDto);
    }

    @GetMapping("/messages/fetch/{chatId}")
    public ResponseEntity<MessagesInChatListRetrievalResponseDto> fetchFromChat(@PathVariable Long chatId, HttpServletRequest request) {
        MessagesInChatListRetrievalRequestDto requestDto = new MessagesInChatListRetrievalRequestDto(
                httpServletRequestHandler.extractUsername(request),
                chatId
        );
        MessagesInChatListRetrievalResponseDto responseDto = messageFacade.fetchMessagesInChat(requestDto);
        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseDto);
    }
}

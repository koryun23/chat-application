package com.chat.security;

import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.entity.user.User;
import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.user.UserService;
import com.chat.service.core.websocket.WebSocketAuthenticatorService;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.management.j2ee.statistics.JMSEndpointStats;

@Component
public class AuthChannelInterceptorAdapter implements ChannelInterceptor {

    private static final String USERNAME_HEADER = "login";
    private static final String PASSWORD_HEADER = "passcode";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final WebSocketAuthenticatorService webSocketAuthenticatorService;
    private final JwtService jwtService;
    private final UserService userService;
    private final MessageConverter messageConverter;

    public AuthChannelInterceptorAdapter(WebSocketAuthenticatorService webSocketAuthenticatorService, JwtService jwtService, UserService userService, MessageConverter messageConverter) {
        this.webSocketAuthenticatorService = webSocketAuthenticatorService;
        this.jwtService = jwtService;
        this.userService = userService;
        this.messageConverter = messageConverter;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

        System.out.println(accessor.getCommand());

        if(StompCommand.CONNECT == accessor.getCommand()) {

            System.out.println("connecting");

            String token = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER).substring(7);
            UsernamePasswordAuthenticationToken user = webSocketAuthenticatorService.getAuthenticatedOrFail(token);
            accessor.setUser(user);
            accessor.setLogin((String)user.getPrincipal());
        }

        return message;

    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if(accessor.getCommand() == StompCommand.SEND) {
            System.out.println(message.getPayload().getClass().getSimpleName());
            System.out.println("Successfully sent the message, payload - " + message.getPayload());
        }
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        Object payload = message.getPayload();
        System.out.println(payload.getClass().getName());
        if(payload instanceof SendPrivateMessageRequestDto) {
            SendPrivateMessageRequestDto sendPrivateMessageRequestDto = (SendPrivateMessageRequestDto) payload;
            System.out.println("Successfully received the message - " + sendPrivateMessageRequestDto);
        }
        System.out.println("Successfully received the message, payload - " + message.getPayload());
        return message;
    }
}

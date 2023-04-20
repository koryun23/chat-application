package com.chat.security;

import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.user.UserService;
import com.chat.service.core.websocket.WebSocketAuthenticatorService;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AuthChannelInterceptorAdapter implements ChannelInterceptor {

    private static final String USERNAME_HEADER = "login";
    private static final String PASSWORD_HEADER = "passcode";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final WebSocketAuthenticatorService webSocketAuthenticatorService;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthChannelInterceptorAdapter(WebSocketAuthenticatorService webSocketAuthenticatorService, JwtService jwtService, UserService userService) {
        this.webSocketAuthenticatorService = webSocketAuthenticatorService;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println();
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        System.out.println();
        if(StompCommand.CONNECT == accessor.getCommand()) {

            String token = accessor.getFirstNativeHeader(AUTHORIZATION_HEADER).substring(7);
            UsernamePasswordAuthenticationToken user = webSocketAuthenticatorService.getAuthenticatedOrFail(token);
            accessor.setUser(user);
            System.out.println();
        }
        return message;
    }
}

package com.chat.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketAuthorizationSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages.anyMessage().authenticated()
                .simpTypeMatchers(SimpMessageType.CONNECT,
                                  SimpMessageType.DISCONNECT,
                                  SimpMessageType.OTHER,
                                  SimpMessageType.UNSUBSCRIBE,
                                  SimpMessageType.HEARTBEAT,
                                  SimpMessageType.MESSAGE).permitAll()
                .simpDestMatchers("/**").permitAll();
    }

    @Override
    protected boolean sameOriginDisabled() {
        return true;
    }
}

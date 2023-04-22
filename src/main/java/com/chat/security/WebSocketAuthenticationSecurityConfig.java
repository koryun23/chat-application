package com.chat.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.util.Assert;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//@Configuration
//@Order(Ordered.HIGHEST_PRECEDENCE + 99)
public class WebSocketAuthenticationSecurityConfig implements WebSocketMessageBrokerConfigurer {

    private final AuthChannelInterceptorAdapter authChannelInterceptorAdapter;

    public WebSocketAuthenticationSecurityConfig(AuthChannelInterceptorAdapter authChannelInterceptorAdapter) {
        Assert.notNull(authChannelInterceptorAdapter, "Authentication Channel Interceptor Adapter must not be null");
        this.authChannelInterceptorAdapter = authChannelInterceptorAdapter;
    }


}

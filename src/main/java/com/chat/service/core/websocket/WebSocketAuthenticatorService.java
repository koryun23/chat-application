package com.chat.service.core.websocket;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface WebSocketAuthenticatorService {
    UsernamePasswordAuthenticationToken getAuthenticatedOrFail(String token);
}

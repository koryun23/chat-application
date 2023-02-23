package com.chat.handler;

import com.chat.service.core.jwt.JwtService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HttpServletRequestHandlerImpl implements HttpServletRequestHandler {

    private final JwtService jwtService;

    public HttpServletRequestHandlerImpl(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public String extractUsername(HttpServletRequest request) {
        return jwtService.getUsername(extractToken(request));
    }

    public String extractToken(HttpServletRequest request) {
        return request.getHeader("Authorization").substring(7);
    }
}

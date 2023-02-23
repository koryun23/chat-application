package com.chat.handler;

import javax.servlet.http.HttpServletRequest;

public interface HttpServletRequestHandler {

    String extractUsername(HttpServletRequest request);

    String extractToken(HttpServletRequest request);
}

package com.chat.exceptions;

public class ChatNotFoundException extends RuntimeException {

    public ChatNotFoundException(Long id) {
        super(String.format("No chat found with an id of %s", id));
    }
}

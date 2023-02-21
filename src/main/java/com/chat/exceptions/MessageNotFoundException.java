package com.chat.exceptions;

public class MessageNotFoundException extends RuntimeException{

    public MessageNotFoundException(Long id) {
        super(String.format("No message found having an id of %s", id));
    }
}

package com.chat.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("No user was found with an id of %s", id));
    }
}

package com.chat.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("No user was found with an id of %s", id));
    }

    public UserNotFoundException(String username) {
        super(String.format("No user was found with a username of %s", username));
    }
}

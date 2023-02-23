package com.chat.exceptions;

import com.chat.entity.user.User;

public class UserChatNotFoundException extends RuntimeException {

    public UserChatNotFoundException(Long id) {
        super(String.format("No UserChat found with an id of %s", id));
    }
}

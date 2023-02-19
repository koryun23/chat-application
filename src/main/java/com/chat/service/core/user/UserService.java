package com.chat.service.core.user;

import com.chat.entity.user.User;

import java.util.Optional;

public interface UserService {

    User create(UserCreationParams params);

    User getById(Long id);

    Optional<User> findByUsername(String username);
}

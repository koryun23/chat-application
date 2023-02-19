package com.chat.service.core.user;

import com.chat.entity.user.User;

public interface UserService {

    User create(UserCreationParams params);

    User getById(Long id);
}

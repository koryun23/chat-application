package com.chat.mapper.core.user;

import com.chat.entity.user.User;
import com.chat.service.core.user.UserCreationParams;

import java.util.function.Function;

public interface UserCreationParamsToUserMapper extends Function<UserCreationParams, User> {

}

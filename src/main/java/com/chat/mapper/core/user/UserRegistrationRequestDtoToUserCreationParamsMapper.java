package com.chat.mapper.core.user;

import com.chat.dto.request.UserRegistrationRequestDto;
import com.chat.service.core.user.UserCreationParams;

import java.util.function.Function;

public interface UserRegistrationRequestDtoToUserCreationParamsMapper extends Function<UserRegistrationRequestDto, UserCreationParams> {
}

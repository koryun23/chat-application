package com.chat.mapper.impl.user;

import com.chat.dto.request.UserRegistrationRequestDto;
import com.chat.mapper.core.user.UserRegistrationRequestDtoToUserCreationParamsMapper;
import com.chat.service.core.user.UserCreationParams;

import java.time.LocalDateTime;

public class UserRegistrationRequestDtoToUserCreationParamsMapperImpl implements UserRegistrationRequestDtoToUserCreationParamsMapper {
    @Override
    public UserCreationParams apply(UserRegistrationRequestDto requestDto) {
        return new UserCreationParams(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getFirstName(),
                requestDto.getSecondName(),
                LocalDateTime.now()
        );
    }
}

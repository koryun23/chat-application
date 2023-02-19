package com.chat.facade.core.user;

import com.chat.dto.request.UserRegistrationRequestDto;
import com.chat.dto.response.UserRegistrationResponseDto;

public interface UserFacade {

    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto);
}

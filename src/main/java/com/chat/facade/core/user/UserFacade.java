package com.chat.facade.core.user;

import com.chat.dto.request.UserListRetrievalRequestDto;
import com.chat.dto.request.UserRegistrationRequestDto;
import com.chat.dto.request.UserRetrievalRequestDto;
import com.chat.dto.response.UserListRetrievalResponseDto;
import com.chat.dto.response.UserRegistrationResponseDto;
import com.chat.dto.response.UserRetrievalResponseDto;

public interface UserFacade {

    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto);

    UserRetrievalResponseDto retrieveSingleUser(UserRetrievalRequestDto requestDto);

    UserListRetrievalResponseDto retrieveMultipleUsers(UserListRetrievalRequestDto requestDto);
}

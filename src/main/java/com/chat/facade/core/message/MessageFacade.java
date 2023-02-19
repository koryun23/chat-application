package com.chat.facade.core.message;

import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.dto.request.SendPublicMessageRequestDto;
import com.chat.dto.response.SendPrivateMessageResponseDto;
import com.chat.dto.response.SendPublicMessageResponseDto;

public interface MessageFacade {

    SendPrivateMessageResponseDto sendPrivateMessage(SendPrivateMessageRequestDto requestDto);

    SendPublicMessageResponseDto sendPublicMessage(SendPublicMessageRequestDto requestDto);

}

package com.chat.facade.core.chat;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.dto.response.ChatCreationResponseDto;

public interface ChatFacade {

    ChatCreationResponseDto createChat(ChatCreationRequestDto requestDto);
}

package com.chat.facade.core.chat;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.dto.request.ChatDeletionRequestDto;
import com.chat.dto.response.ChatCreationResponseDto;
import com.chat.dto.response.ChatDeletionResponseDto;

public interface ChatFacade {

    ChatCreationResponseDto createChat(ChatCreationRequestDto requestDto);

    ChatDeletionResponseDto deleteChat(ChatDeletionRequestDto requestDto);
}

package com.chat.facade.core.chat;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.dto.request.ChatDeletionRequestDto;
import com.chat.dto.request.ChatUpdateRequestDto;
import com.chat.dto.request.UserChatUpdateRequestDto;
import com.chat.dto.response.ChatCreationResponseDto;
import com.chat.dto.response.ChatDeletionResponseDto;
import com.chat.dto.response.ChatUpdateResponseDto;
import com.chat.dto.response.UserChatUpdateResponseDto;

public interface ChatFacade {

    ChatCreationResponseDto createChat(ChatCreationRequestDto requestDto);

    ChatDeletionResponseDto deleteChat(ChatDeletionRequestDto requestDto);

    ChatUpdateResponseDto updateChat(ChatUpdateRequestDto requestDto);

    UserChatUpdateResponseDto updateUserChat(UserChatUpdateRequestDto requestDto);
}

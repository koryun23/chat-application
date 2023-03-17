package com.chat.facade.core.chat;

import com.chat.dto.request.*;
import com.chat.dto.response.*;

public interface ChatFacade {

    ChatCreationResponseDto createChat(ChatCreationRequestDto requestDto);

    ChatDeletionResponseDto deleteChat(ChatDeletionRequestDto requestDto);

    ChatUpdateResponseDto updateChat(ChatUpdateRequestDto requestDto);

    UserChatUpdateResponseDto updateUserChat(UserChatUpdateRequestDto requestDto);

    UserChatCreationResponseDto createUserChat(UserChatCreationRequestDto requestDto);

    UserChatRetrievalResponseDto retrieveUsersInChat(UserChatRetrievalRequestDto requestDto);
}

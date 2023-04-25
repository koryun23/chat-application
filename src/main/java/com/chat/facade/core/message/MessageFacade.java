package com.chat.facade.core.message;

import com.chat.dto.request.MessagesInChatListRetrievalRequestDto;
import com.chat.dto.request.SendNotificationRequestDto;
import com.chat.dto.request.SendPrivateMessageRequestDto;
import com.chat.dto.request.SendPublicMessageRequestDto;
import com.chat.dto.response.MessagesInChatListRetrievalResponseDto;
import com.chat.dto.response.SendNotificationResponseDto;
import com.chat.dto.response.SendPrivateMessageResponseDto;
import com.chat.dto.response.SendPublicMessageResponseDto;

import javax.servlet.http.HttpServletRequest;

public interface MessageFacade {

    SendPrivateMessageResponseDto sendPrivateMessage(SendPrivateMessageRequestDto requestDto);

    SendPublicMessageResponseDto sendPublicMessage(SendPublicMessageRequestDto requestDto);

    SendPrivateMessageResponseDto savePrivateMessage(SendPrivateMessageRequestDto requestDto);

    SendPublicMessageResponseDto savePublicMessage(SendPublicMessageRequestDto requestDto);

    SendNotificationResponseDto sendNotification(SendNotificationRequestDto requestDto);

    MessagesInChatListRetrievalResponseDto fetchMessagesInChat(MessagesInChatListRetrievalRequestDto requestDto);

}

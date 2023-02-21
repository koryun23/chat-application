package com.chat.mapper.core.chat;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.service.core.chat.ChatCreationParams;

import java.util.function.Function;

public interface ChatCreationRequestDtoToChatCreationParamsMapper extends Function<ChatCreationRequestDto, ChatCreationParams> {
}

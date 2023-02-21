package com.chat.mapper.impl.chat;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.entity.chat.Chat;
import com.chat.mapper.core.chat.ChatCreationParamsToChatMapper;
import com.chat.mapper.core.chat.ChatCreationRequestDtoToChatCreationParamsMapper;
import com.chat.service.core.chat.ChatCreationParams;

import java.time.LocalDateTime;

public class ChatCreationRequestDtoToChatCreationParamsMapperImpl implements ChatCreationRequestDtoToChatCreationParamsMapper {

    @Override
    public ChatCreationParams apply(ChatCreationRequestDto requestDto) {
        return new ChatCreationParams(
                requestDto.getName(),
                requestDto.getChatType(),
                LocalDateTime.now()
        );
    }
}

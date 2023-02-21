package com.chat.mapper.impl.chat;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.mapper.core.chat.ChatCreationRequestDtoToChatCreationParamsMapper;
import com.chat.service.core.chat.ChatCreationParams;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
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

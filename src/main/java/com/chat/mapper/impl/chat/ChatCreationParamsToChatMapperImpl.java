package com.chat.mapper.impl.chat;

import com.chat.entity.chat.Chat;
import com.chat.mapper.core.chat.ChatCreationParamsToChatMapper;
import com.chat.service.core.chat.ChatCreationParams;

public class ChatCreationParamsToChatMapperImpl implements ChatCreationParamsToChatMapper {
    @Override
    public Chat apply(ChatCreationParams params) {
        return new Chat(
                params.getName(),
                params.getCreatedAt(),
                params.getChatType()
        );
    }
}

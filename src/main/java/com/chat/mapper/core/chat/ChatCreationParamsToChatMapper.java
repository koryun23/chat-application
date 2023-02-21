package com.chat.mapper.core.chat;

import com.chat.entity.chat.Chat;
import com.chat.service.core.chat.ChatCreationParams;

import java.util.function.Function;

public interface ChatCreationParamsToChatMapper extends Function<ChatCreationParams, Chat> {
}

package com.chat.service.core.chat;

import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;

public interface ChatService {

    Chat createChat(ChatCreationParams params);

    Chat getById(Long id);
}

package com.chat.service.core.chat;

import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;

import java.util.Optional;

public interface ChatService {

    Chat createChat(ChatCreationParams params);

    Chat getById(Long id);

    void delete(Long id);

    Optional<Chat> findById(Long id);
}

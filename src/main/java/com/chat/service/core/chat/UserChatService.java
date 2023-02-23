package com.chat.service.core.chat;

import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;

import java.util.List;
import java.util.Optional;

public interface UserChatService {

    UserChat createUserChat(UserChatCreationParams params);

    UserChat updateUserChat(UserChatUpdateParams params);

    UserChat getById(Long id);

    Optional<UserChat> findById(Long id);

    List<UserChat> getAll(Long chatId);
}

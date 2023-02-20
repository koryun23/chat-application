package com.chat.service.core.chat;

import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;

public interface UserChatService {

    UserChat createUserChat(UserChatCreationParams params);

}

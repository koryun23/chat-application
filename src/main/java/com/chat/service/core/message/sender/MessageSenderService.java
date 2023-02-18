package com.chat.service.core.message.sender;

import com.chat.service.impl.message.sender.PrivateMessageCreationParams;
import com.chat.service.impl.message.sender.PublicMessageCreationParams;

public interface MessageSenderService {

    void sendPrivateMessage(PrivateMessageCreationParams privateMessageCreationParams);

    void sendPublicMessage(PublicMessageCreationParams publicMessageCreationParams);
}

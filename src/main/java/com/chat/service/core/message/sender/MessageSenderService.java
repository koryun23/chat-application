package com.chat.service.core.message.sender;

public interface MessageSenderService {

    void sendPrivateMessage(PrivateMessageCreationParams privateMessageCreationParams);

    void sendPublicMessage(PublicMessageCreationParams publicMessageCreationParams);
}

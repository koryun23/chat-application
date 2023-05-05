package com.chat.service.core.message.sender;

import com.chat.dto.plain.MessageDto;

public interface MessageSenderService {

    void sendPrivateMessage(MessageDto messageDto);

    void sendPublicMessage(PublicMessageCreationParams publicMessageCreationParams);
}

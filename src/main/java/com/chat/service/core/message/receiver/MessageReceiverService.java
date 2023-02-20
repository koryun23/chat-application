package com.chat.service.core.message.receiver;

import com.chat.service.core.message.sender.PrivateMessageCreationParams;
import com.chat.service.core.message.sender.PublicMessageCreationParams;

public interface MessageReceiverService {

    void receivePrivateMessage(PrivateMessageCreationParams privateMessageCreationParams);

    void receivePublicMessage(PublicMessageCreationParams publicMessageCreationParams);
}

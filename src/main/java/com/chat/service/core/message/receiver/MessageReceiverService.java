package com.chat.service.core.message.receiver;

import com.chat.service.impl.message.sender.PrivateMessageCreationParams;
import com.chat.service.impl.message.sender.PublicMessageCreationParams;

public interface MessageReceiverService {

    void receivePrivateMessage(PrivateMessageCreationParams privateMessageCreationParams);

    void receivePublicMessage(PublicMessageCreationParams publicMessageCreationParams);
}

package com.chat.mapper.impl.message;

import com.chat.entity.message.Message;
import com.chat.mapper.core.message.MessageCreationParamsToMessageMapper;
import com.chat.service.core.message.persist.MessageCreationParams;
import org.springframework.stereotype.Component;

@Component
public class MessageCreationParamsToMessageMapperImpl implements MessageCreationParamsToMessageMapper {

    @Override
    public Message apply(MessageCreationParams messageCreationParams) {
        return null;
    }
}

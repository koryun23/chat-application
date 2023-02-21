package com.chat.mapper.core.message;

import com.chat.entity.message.Message;
import com.chat.service.core.message.persist.MessageCreationParams;

import java.util.function.Function;

public interface MessageCreationParamsToMessageMapper extends Function<MessageCreationParams, Message> {
}

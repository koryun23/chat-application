package com.chat.service.impl.chat;

import com.chat.entity.chat.Chat;
import com.chat.exceptions.ChatNotFoundException;
import com.chat.mapper.core.chat.ChatCreationParamsToChatMapper;
import com.chat.repository.ChatRepository;
import com.chat.repository.UserChatRepository;
import com.chat.service.core.chat.ChatCreationParams;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.user.UserService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final UserChatRepository userChatRepository;
    private final UserService userService;
    private final ChatCreationParamsToChatMapper chatCreationParamsToChatMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);

    public ChatServiceImpl(ChatRepository chatRepository, UserChatRepository userChatRepository, UserService userService, ChatCreationParamsToChatMapper chatCreationParamsToChatMapper) {
        this.chatRepository = chatRepository;
        this.userChatRepository = userChatRepository;
        this.userService = userService;
        this.chatCreationParamsToChatMapper = chatCreationParamsToChatMapper;
    }

    @Transactional
    @Override
    public Chat createChat(ChatCreationParams params) {
        LOGGER.info("Creating a chat according to Chat creation params - {}", params);
        Assert.notNull(params, "Chat creation params must not be null");
        Chat savedChat = chatRepository.save(chatCreationParamsToChatMapper.apply(params));
        LOGGER.info("Successfully created a chat according to the chat creation params - {}, result - {}", params, savedChat);
        return savedChat;
    }

    @Override
    public Chat getById(Long id) {
        LOGGER.info("Retrieving a chat with an id of {}", id);
        Assert.notNull(id, "Chat id must not be null when trying to retrieve a chat");
        Chat chat = chatRepository.findById(id).orElseThrow(() -> new ChatNotFoundException(id));
        LOGGER.info("Successfully retrieved a chat with an id of {}, result - {}", id, chat);
        return chat;
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting a chat with an id of {}", id);
        Assert.notNull(id, "Chat id must not be null when trying to delete it");
        chatRepository.deleteById(id);
        LOGGER.info("Successfully deleted a chat with an id of {}", id);
    }

    @Override
    public Optional<Chat> findById(Long id) {
        LOGGER.info("Retrieving an optional chat with an id of {}", id);
        Assert.notNull(id, "Chat id must not be null when trying to retrieve its optional");
        Optional<Chat> optionalChat = chatRepository.findById(id);
        LOGGER.info("Successfully retrieved a chat optional with an id of {}, result - {}", id, optionalChat);
        return optionalChat;
    }


}

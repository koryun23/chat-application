package com.chat.service.impl.chat;

import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;
import com.chat.entity.chat.type.ChatType;
import com.chat.exceptions.ChatNotFoundException;
import com.chat.mapper.core.chat.ChatCreationParamsToChatMapper;
import com.chat.repository.ChatRepository;
import com.chat.repository.UserChatRepository;
import com.chat.service.core.chat.ChatCreationParams;
import com.chat.service.core.chat.ChatService;
import com.chat.service.core.chat.ChatUpdateParams;
import com.chat.service.core.user.UserService;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatServiceImpl.class);
    private final ChatRepository chatRepository;
    private final UserChatRepository userChatRepository;
    private final UserService userService;
    private final ChatCreationParamsToChatMapper chatCreationParamsToChatMapper;

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

    @Transactional
    @Override
    public Chat getById(Long id) {
        LOGGER.info("Retrieving a chat with an id of {}", id);
        Assert.notNull(id, "Chat id must not be null when trying to retrieve a chat");
        Chat chat = chatRepository.findById(id).orElseThrow(() -> new ChatNotFoundException(id));
        LOGGER.info("Successfully retrieved a chat with an id of {}, result - {}", id, chat);
        return chat;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting a chat with an id of {}", id);
        Assert.notNull(id, "Chat id must not be null when trying to delete it");
        chatRepository.deleteById(id);
        LOGGER.info("Successfully deleted a chat with an id of {}", id);
    }

    @Transactional
    @Override
    public Optional<Chat> findById(Long id) {
        LOGGER.info("Retrieving an optional chat with an id of {}", id);
        Assert.notNull(id, "Chat id must not be null when trying to retrieve its optional");
        Optional<Chat> optionalChat = chatRepository.findById(id);
        LOGGER.info("Successfully retrieved a chat optional with an id of {}, result - {}", id, optionalChat);
        return optionalChat;
    }

    @Transactional
    @Override
    public Chat update(ChatUpdateParams params) {
        LOGGER.info("Updating chat according to the chat update params - {}", params);
        Assert.notNull(params, "Chat Update Params must not be null");

        Chat existingChat = getById(params.getId());

        Chat chat = new Chat(
                params.getName(),
                existingChat.getCreatedAt(),
                existingChat.getChatType()
        );
        chat.setId(existingChat.getId());
        Chat updatedChat = chatRepository.save(chat);

        LOGGER.info("Successfully updated an existing chat - {}, according to the Chat Update Params - {}, result - {}", existingChat, params, updatedChat);
        return updatedChat;
    }

    @Transactional
    @Override
    public Chat getByName(String name) {
        LOGGER.info("Retrieving a chat with a name of {}", name);
        Assert.notNull(name, "Chat name must not be null");
        Chat chat = chatRepository.findByName(name).orElseThrow(() -> new ChatNotFoundException(name));
        LOGGER.info("Successfully retrieved a chat with a name of {}, result - {}", name, chat);
        return chat;
    }

    @Override
    public List<Chat> findChatsWithSimilarNames(String name) {
        LOGGER.info("Retrieving chats with a name similar to {}", name);
        Assert.notNull(name, "Chat name must not be null");

        List<Chat> chatsWithSimilarNames = new LinkedList<>();
        List<Chat> allChats = chatRepository.findAll();

        for(Chat chat : allChats) {
            String currentName = chat.getName();
            if(currentName.contains(name)) {
                chatsWithSimilarNames.add(chat);
            }
        }

        LOGGER.info("Successfully retrieved chats with a name similar to {}, result - {}", name, chatsWithSimilarNames);
        return chatsWithSimilarNames;
    }

    @Override
    public Optional<Chat> findByName(String name) {
        LOGGER.info("Retrieving an optional of a chat with a name of {}", name);
        Assert.notNull(name, "Chat name must not be null");
        Assert.hasText(name, "Chat name must not be empty");

        Optional<Chat> chatOptional = chatRepository.findByName(name);

        LOGGER.info("Successfully retrieved a chat optional with a name of {}, result - {}", name, chatOptional);

        return chatOptional;
    }

    @Override
    public List<Chat> findAllByUserUsername(String username) {
        Assert.notNull(username, "Username must not be null");
        LOGGER.info("Retrieving all chats that contain the user {}", username);

        List<Chat> allByUsername = chatRepository.findAll();
        List<Chat> result = new LinkedList<>();

        for(Chat chat : allByUsername) {
            for(UserChat userChat : chat.getUsersInChat()) {
                if(userChat.getUser().getUsername().equals(username)) {
                    result.add(chat);
                }
            }
        }

        LOGGER.info("Successfully retrieved all chats that contain the user {}, result - {}", username, result);
        return result;
    }
}

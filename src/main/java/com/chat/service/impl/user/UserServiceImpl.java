package com.chat.service.impl.user;

import com.chat.entity.user.User;
import com.chat.exceptions.UserNotFoundException;
import com.chat.mapper.core.user.UserCreationParamsToUserMapper;
import com.chat.repository.UserRepository;
import com.chat.service.core.user.UserCreationParams;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserCreationParamsToUserMapper userCreationParamsToUserMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserCreationParamsToUserMapper userCreationParamsToUserMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userCreationParamsToUserMapper = userCreationParamsToUserMapper;
    }

    @Transactional
    @Override
    public User create(UserCreationParams params) {
        LOGGER.info("Creating a user according to the User Creation Params - {}", params);
        Assert.notNull(params, "User Creation Params must not be null");
        User savedUser = userRepository.save(userCreationParamsToUserMapper.apply(params));
        LOGGER.info("Successfully created a new user according to the user creation params - {}, result - {}", params, savedUser);
        return savedUser;
    }

    @Transactional
    @Override
    public User getById(Long id) {
        LOGGER.info("Retrieving a user with an id of {}", id);
        Assert.notNull(id, "User id should not be null when trying to retrieve a user");
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        LOGGER.info("Successfully retrieved a user with an id of {}, result - {}", id, user);
        return user;
    }

    @Transactional
    @Override
    public Optional<User> findByUsername(String username) {
        LOGGER.info("Retrieving an optional user with a username of {}", username);
        Assert.notNull(username, "Username should not be null when trying to retrieve an optional user");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        LOGGER.info("Successfully retrieved an optional user with a username of {}, result - {}", username, optionalUser);
        return optionalUser;
    }

    @Transactional
    @Override
    public User getByUsername(String username) {
        LOGGER.info("Retrieving a user with a username of {}", username);
        Assert.notNull(username, "Username should not be null when trying to retrieve a user");
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
        LOGGER.info("Successfully retrieved a user with a username of {}, result - {}", username, user);
        return user;
    }

    @Transactional
    @Override
    public Optional<User> findById(Long id) {
        LOGGER.info("Retrieving an optional user with an if of {}", id);
        Assert.notNull(id, "User id must not be null");
        Optional<User> optionalUser = userRepository.findById(id);
        LOGGER.info("Successfully retrieved an optional user with an id of {}, result - {}", id, optionalUser);
        return optionalUser;
    }

    @Override
    public boolean userWithUsernameOrPasswordExists(String username, String password) {
        LOGGER.info("Checking if user with username {} or password {} exists", username, password);
        Assert.notNull(username, "Username must not be null");
        Assert.hasText(username, "Username must not be empty");
        Assert.notNull(password, "Password must not be null");
        Assert.hasText(password, "Password must not be empty");

        List<User> allUsers = userRepository.findAll();
        for(User user : allUsers) {
            if(passwordEncoder.matches(password, user.getPassword()) || user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}

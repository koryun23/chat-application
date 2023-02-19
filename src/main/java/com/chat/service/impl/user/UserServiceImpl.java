package com.chat.service.impl.user;

import com.chat.entity.user.User;
import com.chat.exceptions.UserNotFoundException;
import com.chat.repository.UserRepository;
import com.chat.service.core.user.UserCreationParams;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User create(UserCreationParams params) {
        LOGGER.info("Creating a user according to the User Creation Params - {}", params);
        Assert.notNull(params, "User Creation Params must not be null");
        User savedUser = userRepository.save(new User(
                params.getUsername(),
                passwordEncoder.encode(params.getPassword()),
                params.getFirstName(),
                params.getSecondName(),
                params.getJoinedAt()
        ));
        LOGGER.info("Successfully created a new user according to the user creation params - {}, result - {}", params, savedUser);
        return savedUser;
    }

    @Override
    public User getById(Long id) {
        LOGGER.info("Retrieving a user with an id of {}", id);
        Assert.notNull(id, "User id should not be null when trying to retrieve a user");
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        LOGGER.info("Successfully retrieved a user with an id of {}, result - {}", id, user);
        return user;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        LOGGER.info("Retrieving an optional user with a username of {}", username);
        Assert.notNull(username, "Username should not be null when trying to retrieve a user");
        Optional<User> optionalUser = userRepository.findByUsername(username);
        LOGGER.info("Successfully retrieved an optional user with a username of {}, result - {}", username, optionalUser);
        return optionalUser;
    }
}

package com.chat.service.impl.user;

import com.chat.entity.role.UserAppRole;
import com.chat.entity.role.type.UserAppRoleType;
import com.chat.repository.UserAppRoleRepository;
import com.chat.service.core.user.UserAppRoleCreationParams;
import com.chat.service.core.user.UserAppRoleService;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAppRoleServiceImpl implements UserAppRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAppRoleServiceImpl.class);
    private final UserAppRoleRepository userAppRoleRepository;
    private final UserService userService;

    public UserAppRoleServiceImpl(UserAppRoleRepository userAppRoleRepository, UserService userService) {
        this.userAppRoleRepository = userAppRoleRepository;
        this.userService = userService;
    }

    @Transactional
    @Override
    public UserAppRole create(UserAppRoleCreationParams params) {
        LOGGER.info("Creating a User App Role according to the user app role creation params - {}", params);
        Assert.notNull(params, "User app role creation params must not be null");
        UserAppRole userAppRole = userAppRoleRepository.save(new UserAppRole(
                userService.getById(params.getUserId()),
                params.getUserAppRoleType()
        ));
        LOGGER.info("Successfully created a user app role according to the user app role creation params - {}, result - {}", params, userAppRole);
        return userAppRole;
    }

    @Transactional
    @Override
    public List<UserAppRoleType> getAppRoleTypesByUsername(String username) {
        LOGGER.info("Retrieving the app role type of the user with a username of {}", username);
        Assert.notNull(username, "Username must not be null when trying to retrieve the role type of a user");
        List<UserAppRoleType> allByUserUsername = userAppRoleRepository.findAllByUserUsername(username).stream()
                .map(UserAppRole::getUserAppRoleType)
                .collect(Collectors.toList());
        LOGGER.info("Successfully retrieved all app role types of the user with a username of {}, result - {}",
                username,
                allByUserUsername);
        return allByUserUsername;

    }
}

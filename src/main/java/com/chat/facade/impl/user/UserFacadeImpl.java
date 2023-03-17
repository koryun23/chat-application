package com.chat.facade.impl.user;

import com.chat.dto.plain.UserDto;
import com.chat.dto.request.UserListRetrievalRequestDto;
import com.chat.dto.request.UserRegistrationRequestDto;
import com.chat.dto.request.UserRetrievalRequestDto;
import com.chat.dto.response.UserListRetrievalResponseDto;
import com.chat.dto.response.UserRegistrationResponseDto;
import com.chat.dto.response.UserRetrievalResponseDto;
import com.chat.entity.role.UserAppRole;
import com.chat.entity.role.type.UserAppRoleType;
import com.chat.entity.user.User;
import com.chat.facade.core.user.UserFacade;
import com.chat.mapper.core.user.UserRegistrationRequestDtoToUserCreationParamsMapper;
import com.chat.service.core.user.UserAppRoleCreationParams;
import com.chat.service.core.user.UserAppRoleService;
import com.chat.service.core.user.UserCreationParams;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserFacadeImpl implements UserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacadeImpl.class);
    private final UserService userService;
    private final UserAppRoleService userAppRoleService;
    private final UserRegistrationRequestDtoToUserCreationParamsMapper userRegistrationRequestDtoToUserCreationParamsMapper;

    public UserFacadeImpl(UserService userService, UserAppRoleService userAppRoleService, UserRegistrationRequestDtoToUserCreationParamsMapper userRegistrationRequestDtoToUserCreationParamsMapper) {
        this.userService = userService;
        this.userAppRoleService = userAppRoleService;
        this.userRegistrationRequestDtoToUserCreationParamsMapper = userRegistrationRequestDtoToUserCreationParamsMapper;
    }

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) {
        LOGGER.info("Registering a user according to the user registration request dto - {}", requestDto);
        Assert.notNull(requestDto, "User registration request dto should not be null");

        if (userService.userWithUsernameOrPasswordExists(requestDto.getUsername(), requestDto.getPassword())) {
            return new UserRegistrationResponseDto(List.of("Cannot register since the credentials are already taken"));
        }

        User user = userService.create(userRegistrationRequestDtoToUserCreationParamsMapper.apply(requestDto));

        List<UserAppRole> userAppRoles = new LinkedList<>();
        for (UserAppRoleType userAppRoleType : requestDto.getUserAppRoleTypes()) {
            userAppRoles.add(userAppRoleService.create(new UserAppRoleCreationParams(
                    user.getId(),
                    userAppRoleType
            )));

        }

        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getSecondName(),
                userAppRoles.stream().map(UserAppRole::getUserAppRoleType).collect(Collectors.toList()),
                user.getJoinedAt()
        );
        LOGGER.info("Successfully registered a user according to the user registration request dto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }

    @Override
    public UserRetrievalResponseDto retrieveSingleUser(UserRetrievalRequestDto requestDto) {
        LOGGER.info("Retrieving a single user according to the UserRetrievalRequestDto - {}", requestDto);
        Assert.notNull(requestDto, "UserRetrievalRequestDto must not be null");

        String retrieverUsername = requestDto.getRetrieverUsername();
        String username = requestDto.getUsername();

        if(!userWithUsernameExists(retrieverUsername)) {
            return new UserRetrievalResponseDto(List.of(String.format("No user found having a username of %s", retrieverUsername)));
        }

        if(!userWithUsernameExists(username)) {
            return new UserRetrievalResponseDto(List.of(String.format("No user found having a username of %s", username)));
        }

        User userByUsername = userService.getByUsername(username);
        UserRetrievalResponseDto responseDto = new UserRetrievalResponseDto(
                new UserDto(
                        userByUsername.getUsername(),
                        userByUsername.getPassword(),
                        userByUsername.getFirstName(),
                        userByUsername.getSecondName(),
                        userByUsername.getJoinedAt()
                ),
                LocalDateTime.now()
        );
        LOGGER.info("Successfully retrieved a single user according to the UserRetrievalRequestDto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }

    private boolean errorsFound(List<String> errors) {
        return errors.size() != 0;
    }

    private boolean userWithUsernameExists(String username) {
        return userService.findByUsername(username).isPresent();
    }
}

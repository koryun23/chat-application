package com.chat.facade.impl.user;

import com.chat.dto.request.UserRegistrationRequestDto;
import com.chat.dto.response.UserRegistrationResponseDto;
import com.chat.entity.role.UserAppRole;
import com.chat.entity.role.type.UserAppRoleType;
import com.chat.entity.user.User;
import com.chat.facade.core.user.UserFacade;
import com.chat.service.core.user.UserAppRoleCreationParams;
import com.chat.service.core.user.UserAppRoleService;
import com.chat.service.core.user.UserCreationParams;
import com.chat.service.core.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@Component
public class UserFacadeImpl implements UserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFacadeImpl.class);
    private final UserService userService;
    private final UserAppRoleService userAppRoleService;

    public UserFacadeImpl(UserService userService, UserAppRoleService userAppRoleService) {
        this.userService = userService;
        this.userAppRoleService = userAppRoleService;
    }

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) {
        LOGGER.info("Registering a user according to the user registration request dto - {}", requestDto);
        Assert.notNull(requestDto, "User registration request dto should not be null");
        User user = userService.create(new UserCreationParams(
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getFirstName(),
                requestDto.getSecondName(),
                LocalDateTime.now()
        ));

        UserAppRole userAppRole = userAppRoleService.create(new UserAppRoleCreationParams(
                user.getId(),
                requestDto.getUserAppRoleType()
        ));
        UserRegistrationResponseDto responseDto = new UserRegistrationResponseDto(
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getSecondName(),
                userAppRole.getUserAppRoleType(),
                user.getJoinedAt()
        );
        LOGGER.info("Successfully registered a user according to the user registration request dto - {}, result - {}", requestDto, responseDto);
        return responseDto;
    }
}

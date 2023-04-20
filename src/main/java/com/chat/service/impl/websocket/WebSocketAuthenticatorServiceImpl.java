package com.chat.service.impl.websocket;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.chat.entity.user.User;
import com.chat.exceptions.UserNotFoundException;
import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.user.UserAppRoleService;
import com.chat.service.core.user.UserService;
import com.chat.service.core.websocket.WebSocketAuthenticatorService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WebSocketAuthenticatorServiceImpl implements WebSocketAuthenticatorService {

    private final UserService userService;
    private final UserAppRoleService userAppRoleService;
    private final JwtService jwtService;

    public WebSocketAuthenticatorServiceImpl(UserService userService, UserAppRoleService userAppRoleService, JwtService jwtService) {
        Assert.notNull(userService, "User service must not be null");
        Assert.notNull(userAppRoleService, "User app role service must not be null");
        Assert.notNull(jwtService, "Jwt service must not be null");
        this.userService = userService;
        this.userAppRoleService = userAppRoleService;
        this.jwtService = jwtService;
    }

    @Override
    public UsernamePasswordAuthenticationToken getAuthenticatedOrFail(String token) {
        Assert.notNull(token, "token must not be null");
        Assert.hasText(token, "token must not be empty");

        if(jwtService.isExpired(token)) {
            throw new TokenExpiredException("Token has expired", JWT.decode(token).getExpiresAtAsInstant());
        }

        String username = jwtService.getUsername(token);
        Optional<User> userOptional = userService.findByUsername(username);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException(username);
        }
        return new UsernamePasswordAuthenticationToken(
                username,
                null,
                userAppRoleService.getAppRoleTypesByUsername(username).stream().map(userAppRoleType -> new SimpleGrantedAuthority(userAppRoleType.name())).collect(Collectors.toList())
        );
    }
}

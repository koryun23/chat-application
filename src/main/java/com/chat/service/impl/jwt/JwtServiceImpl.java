package com.chat.service.impl.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chat.entity.role.type.UserAppRoleType;
import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.user.UserAppRoleService;
import com.chat.service.core.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    private final JwtBuilder jwtBuilder;
    private final JwtParser jwtParser;
    private final UserService userService;
    private final UserAppRoleService userAppRoleService;

    @Value("${jwt.token.expiration}")
    private long expirationMillis;

    public JwtServiceImpl(JwtBuilder jwtBuilder, JwtParser jwtParser, UserService userService, UserAppRoleService userAppRoleService) {
        this.jwtBuilder = jwtBuilder;
        this.jwtParser = jwtParser;
        this.userService = userService;
        this.userAppRoleService = userAppRoleService;
    }

    @Override
    public String createToken(String username, List<UserAppRoleType> roles) {
        return jwtBuilder
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
                .claim("tokenId", UUID.randomUUID().toString())
                .claim("username", username)
                .claim("authorities", roles)
                .compact();
    }

    @Override
    public String getUsername(String token) {
        Claims body = (Claims) jwtParser.parse(token).getBody();
        return (String) body.get("username");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String> getAuthorities(String token) {
        Claims body = (Claims) jwtParser.parse(token).getBody();
        return (List<String>) body.get("authorities");
    }

    @Override
    public boolean isExpired(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(new Date(System.currentTimeMillis()));
    }

    @Override
    public String getRefreshToken(String username) {
        return createToken(
                username,
                this.userAppRoleService.getAppRoleTypesByUsername(username)
        );
    }
}

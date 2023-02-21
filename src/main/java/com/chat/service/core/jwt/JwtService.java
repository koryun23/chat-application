package com.chat.service.core.jwt;

import com.chat.entity.role.type.UserAppRoleType;

import java.util.List;

public interface JwtService {

    String createToken(String username, List<UserAppRoleType> roles);

    String getUsername(String token);

    List<String> getAuthorities(String token);

    boolean isExpired(String token);

    String getRefreshToken(String username);
}

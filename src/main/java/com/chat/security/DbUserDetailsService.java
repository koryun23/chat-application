package com.chat.security;

import com.chat.entity.role.UserAppRole;
import com.chat.entity.user.User;
import com.chat.service.core.user.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DbUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public DbUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "Username should not be null");
        User dbUser = userService.getByUsername(username);
        return new org.springframework.security.core.userdetails.User(
                username,
                dbUser.getPassword(),
                Stream.of(dbUser.getUserAppRole())
                        .map(UserAppRole::getUserAppRoleType)
                        .map(Enum::name)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList())
        );
    }
}

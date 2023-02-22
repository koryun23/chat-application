package com.chat.security;

import com.chat.dto.response.SuccessfulAuthenticationResponseDto;
import com.chat.entity.role.UserAppRole;
import com.chat.entity.user.User;
import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.user.UserAppRoleService;
import com.chat.service.core.user.UserService;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserAppRoleService userAppRoleService;
    private final JwtService jwtService;

    @Value("${jwt.secret.key}")
    private String secretKey;
    @Value("${jwt.token.expiration}")
    private long expirationMillis;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService, UserAppRoleService userAppRoleService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.userAppRoleService = userAppRoleService;
        this.jwtService = jwtService;
        setFilterProcessesUrl("/auth");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User creds = new ObjectMapper().readValue(request.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            Collections.emptyList()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        String username = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();
        String token = jwtService.createToken(username, userAppRoleService.getAppRoleTypesByUsername(username));

        User user = userService.getByUsername(username);

        SuccessfulAuthenticationResponseDto responseDto = new SuccessfulAuthenticationResponseDto(
                token,
                username,
                user.getPassword(),
                user.getFirstName(),
                user.getSecondName(),
                user.getUserAppRoles().stream().map(UserAppRole::getUserAppRoleType).collect(Collectors.toList())
        );

        response.setHeader("token", token);
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseDto));
        response.getWriter().flush();
    }
}

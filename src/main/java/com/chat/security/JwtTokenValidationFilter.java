package com.chat.security;

import com.chat.service.core.jwt.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenValidationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtTokenValidationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (invalidToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        removeTokenPrefix(token);
        if (jwtService.isExpired(token)) {
            response.setStatus(401);
            return;
        }
        String username = jwtService.getUsername(token);
        Set<SimpleGrantedAuthority> simpleGrantedAuthoritySet = jwtService.getAuthorities(token)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        if (username != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, simpleGrantedAuthoritySet
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            SecurityContextHolder.getContext().setAuthentication(null);
        }

        filterChain.doFilter(request, response);
    }

    private boolean invalidToken(String token) {
        return token == null || !token.startsWith("Bearer ");
    }

    private void removeTokenPrefix(String token) {
        token.substring(7);
    }
}

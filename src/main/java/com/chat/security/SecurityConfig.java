package com.chat.security;

import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.user.UserAppRoleService;
import com.chat.service.core.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final UserAppRoleService userAppRoleService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService userDetailsService, UserService userService, UserAppRoleService userAppRoleService, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.userAppRoleService = userAppRoleService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager(), userService, userAppRoleService, jwtService);
        JwtTokenValidationFilter jwtTokenValidationFilter = new JwtTokenValidationFilter(jwtService);
        http.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterAfter(jwtTokenValidationFilter, JwtAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/join").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/chat/create").permitAll()
                .antMatchers(HttpMethod.DELETE, "/chat/delete/**").hasAnyAuthority("SUPERADMIN", "OWNER")
                .antMatchers(HttpMethod.PUT, "chat/update/**").hasAnyAuthority("SUPERADMIN", "OWNER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "user-chat/update/**").hasAnyAuthority("SUPERADMIN", "OWNER", "ADMIN")
                .antMatchers(HttpMethod.POST, "user-chat/create/**").hasAnyAuthority("SUPERADMIN", "OWNER", "ADMIN")
                .antMatchers(HttpMethod.GET, "user-chat").permitAll()
                .antMatchers(HttpMethod.GET, "users/**").permitAll()
                .antMatchers("/message").permitAll()
                .antMatchers("/private-message").permitAll()
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000"));
        corsConfiguration.setAllowedMethods(List.of(
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.DELETE.name()
        ));
        corsConfiguration.setAllowedHeaders(List.of("*"));
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}

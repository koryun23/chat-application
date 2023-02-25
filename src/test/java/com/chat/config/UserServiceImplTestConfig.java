package com.chat.config;

import com.chat.mapper.core.user.UserCreationParamsToUserMapper;
import com.chat.mapper.impl.user.UserCreationParamsToUserMapperImpl;
import com.chat.repository.UserRepository;
import com.chat.service.core.user.UserService;
import com.chat.service.impl.user.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceImplTestConfig {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserCreationParamsToUserMapper userCreationParamsToUserMapper) {
        return new UserServiceImpl(userRepository, passwordEncoder, userCreationParamsToUserMapper);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserCreationParamsToUserMapper userCreationParamsToUserMapper(PasswordEncoder passwordEncoder) {
        return new UserCreationParamsToUserMapperImpl(passwordEncoder);
    }
}

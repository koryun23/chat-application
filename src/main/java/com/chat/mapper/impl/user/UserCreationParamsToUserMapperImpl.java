package com.chat.mapper.impl.user;

import com.chat.entity.user.User;
import com.chat.mapper.core.user.UserCreationParamsToUserMapper;
import com.chat.service.core.user.UserCreationParams;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserCreationParamsToUserMapperImpl implements UserCreationParamsToUserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserCreationParamsToUserMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User apply(UserCreationParams params) {
        return new User(
                params.getUsername(),
                passwordEncoder.encode(params.getPassword()),
                params.getFirstName(),
                params.getSecondName(),
                params.getJoinedAt()
        );
    }
}

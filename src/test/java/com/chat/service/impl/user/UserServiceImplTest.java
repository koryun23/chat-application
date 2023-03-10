package com.chat.service.impl.user;

import com.chat.config.TestConfig;
import com.chat.config.UserServiceImplTestConfig;
import com.chat.entity.user.User;
import com.chat.repository.UserRepository;
import com.chat.service.core.user.UserCreationParams;
import com.chat.service.core.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceImplTestConfig.class, TestConfig.class})
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test_userCreate() {

        Assertions.assertThat(userRepository.findAll()).isEmpty();
        userService.create(new UserCreationParams(
                "username",
                "password",
                "firstName",
                "secondName",
                LocalDateTime.MAX
        ));
        Assertions.assertThat(userRepository.findAll()).isNotEmpty();
        Optional<User> userOptional = userRepository.findByUsername("username");
        Assertions.assertThat(userOptional).isNotEmpty();
        User user = userOptional.get();
        Assertions.assertThat(user.getPassword()).isEqualTo("password");
        Assertions.assertThat(user.getFirstName()).isEqualTo("firstName");
        Assertions.assertThat(user.getSecondName()).isEqualTo("secondName");
        Assertions.assertThat(user.getJoinedAt()).isEqualTo(LocalDateTime.MAX);
    }
}
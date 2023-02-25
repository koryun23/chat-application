package com.chat.service.impl.user;

import com.chat.config.TestConfig;
import com.chat.config.UserServiceImplTestConfig;
import com.chat.service.core.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserServiceImplTestConfig.class, TestConfig.class})
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testUserService() {

    }
}
package com.chat.loader;

import com.chat.entity.role.UserAppRole;
import com.chat.entity.role.type.UserAppRoleType;
import com.chat.entity.user.User;
import com.chat.repository.UserAppRoleRepository;
import com.chat.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseLoaderImpl implements DatabaseLoader, CommandLineRunner {

    private final UserRepository userRepository;
    private final UserAppRoleRepository userAppRoleRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(DatabaseLoaderImpl.class);

    public DatabaseLoaderImpl(UserRepository userRepository, UserAppRoleRepository userAppRoleRepository) {
        this.userRepository = userRepository;
        this.userAppRoleRepository = userAppRoleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        saveUserAppRole(saveUser());
    }

    private User saveUser() {
        if(userRepository.findByUsername("SUPERADMIN").isPresent()) {
            return null;
        }

        User user = new User(
                "SUPERADMIN",
                "$2a$10$pT/2SOTRuQlw16aAiOI68uT/KT1nGBEM404hhp9zyxa1flIoc0HLe",
                "John",
                "Williams",
                LocalDateTime.now()
        );
        userRepository.save(user);
        return user;
    }

    private void saveUserAppRole(User user) {
        if(user == null) return;
        userAppRoleRepository.save(new UserAppRole(
                user, UserAppRoleType.APP_SUPERADMIN
        ));
    }
}

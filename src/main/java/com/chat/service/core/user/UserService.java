package com.chat.service.core.user;

import com.chat.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(UserCreationParams params);

    User getById(Long id);

    Optional<User> findByUsername(String username);

    User getByUsername(String username);

    Optional<User> findById(Long id);

    boolean userWithUsernameOrPasswordExists(String username, String password);

    List<User> findUsersWithSimilarFirstNames(String firstName);

    List<User> findUsersWithSimilarSecondNames(String secondName);

    List<User> findUsersWithSimilarUsernames(String username);
}

package com.chat.repository;

import com.chat.entity.role.UserAppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAppRoleRepository extends JpaRepository<UserAppRole, Long> {
    List<UserAppRole> findAllByUserUsername(String username);
}

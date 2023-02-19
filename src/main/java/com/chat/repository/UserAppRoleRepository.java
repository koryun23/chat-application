package com.chat.repository;

import com.chat.entity.role.UserAppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAppRoleRepository extends JpaRepository<UserAppRole, Long> {

}

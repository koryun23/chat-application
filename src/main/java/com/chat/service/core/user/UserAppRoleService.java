package com.chat.service.core.user;

import com.chat.entity.role.UserAppRole;
import com.chat.entity.role.type.UserAppRoleType;

import java.util.List;

public interface UserAppRoleService {

    UserAppRole create(UserAppRoleCreationParams params);

    List<UserAppRoleType> getAppRoleTypesByUsername(String username);
}

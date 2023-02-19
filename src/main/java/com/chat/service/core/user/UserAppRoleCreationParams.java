package com.chat.service.core.user;

import com.chat.entity.role.type.UserAppRoleType;

import java.util.Objects;

public class UserAppRoleCreationParams {

    private Long userId;
    private UserAppRoleType userAppRoleType;

    public UserAppRoleCreationParams() {
    }

    public UserAppRoleCreationParams(Long userId, UserAppRoleType userAppRoleType) {
        this.userId = userId;
        this.userAppRoleType = userAppRoleType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserAppRoleType getUserAppRoleType() {
        return userAppRoleType;
    }

    public void setUserAppRoleType(UserAppRoleType userAppRoleType) {
        this.userAppRoleType = userAppRoleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAppRoleCreationParams that = (UserAppRoleCreationParams) o;
        return Objects.equals(userId, that.userId) && userAppRoleType == that.userAppRoleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userAppRoleType);
    }

    @Override
    public String toString() {
        return "UserAppRoleCreationParams{" +
                "userId=" + userId +
                ", userAppRoleType=" + userAppRoleType +
                '}';
    }
}

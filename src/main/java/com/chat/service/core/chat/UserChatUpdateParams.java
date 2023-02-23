package com.chat.service.core.chat;

import com.chat.entity.role.type.UserChatRoleType;

import java.util.Objects;

public class UserChatUpdateParams {

    private Long userChatId;
    private UserChatRoleType userChatRoleType;

    public UserChatUpdateParams() {
    }

    public UserChatUpdateParams(Long userChatId, UserChatRoleType userChatRoleType) {
        this.userChatId = userChatId;
        this.userChatRoleType = userChatRoleType;
    }

    public Long getUserChatId() {
        return userChatId;
    }

    public void setUserChatId(Long userChatId) {
        this.userChatId = userChatId;
    }

    public UserChatRoleType getUserChatRoleType() {
        return userChatRoleType;
    }

    public void setUserChatRoleType(UserChatRoleType userChatRoleType) {
        this.userChatRoleType = userChatRoleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChatUpdateParams that = (UserChatUpdateParams) o;
        return Objects.equals(userChatId, that.userChatId) && userChatRoleType == that.userChatRoleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userChatId, userChatRoleType);
    }

    @Override
    public String toString() {
        return "UserChatUpdateParams{" +
                "userChatId=" + userChatId +
                ", userChatRoleType=" + userChatRoleType +
                '}';
    }
}

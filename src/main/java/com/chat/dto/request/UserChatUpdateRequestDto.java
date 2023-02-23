package com.chat.dto.request;

import com.chat.entity.role.type.UserChatRoleType;

import java.util.Objects;

public class UserChatUpdateRequestDto {

    private Long id;
    private UserChatRoleType userChatRoleType;
    private String updaterUsername;

    public UserChatUpdateRequestDto(Long id, UserChatRoleType userChatRoleType, String updaterUsername) {
        this.id = id;
        this.userChatRoleType = userChatRoleType;
        this.updaterUsername = updaterUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserChatRoleType getUserChatRoleType() {
        return userChatRoleType;
    }

    public void setUserChatRoleType(UserChatRoleType userChatRoleType) {
        this.userChatRoleType = userChatRoleType;
    }

    public String getUpdaterUsername() {
        return updaterUsername;
    }

    public void setUpdaterUsername(String updaterUsername) {
        this.updaterUsername = updaterUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChatUpdateRequestDto that = (UserChatUpdateRequestDto) o;
        return Objects.equals(id, that.id) && userChatRoleType == that.userChatRoleType && Objects.equals(updaterUsername, that.updaterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userChatRoleType, updaterUsername);
    }

    @Override
    public String toString() {
        return "UserChatUpdateRequestDto{" +
                "id=" + id +
                ", userChatRoleType=" + userChatRoleType +
                ", updaterUsername=" + updaterUsername +
                '}';
    }
}

package com.chat.dto.response;

import com.chat.entity.role.type.UserChatRoleType;

import java.util.List;
import java.util.Objects;

public class UserChatUpdateResponseDto {

    private Long id;
    private UserChatRoleType userChatRoleType;
    private String updaterUsername;

    private List<String> errors;

    public UserChatUpdateResponseDto() {
    }

    public UserChatUpdateResponseDto(Long id, UserChatRoleType userChatRoleType, String updaterUsername) {
        this.id = id;
        this.userChatRoleType = userChatRoleType;
        this.updaterUsername = updaterUsername;
    }

    public UserChatUpdateResponseDto(List<String> errors) {
        this.errors = errors;
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

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChatUpdateResponseDto that = (UserChatUpdateResponseDto) o;
        return Objects.equals(id, that.id) && userChatRoleType == that.userChatRoleType && Objects.equals(errors, that.errors) && Objects.equals(updaterUsername, that.updaterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userChatRoleType, updaterUsername, errors);
    }

    @Override
    public String toString() {
        return "UserChatUpdateResponseDto{" +
                "id=" + id +
                ", userChatRoleType=" + userChatRoleType +
                ", updaterUsername=" + updaterUsername +
                ", errors=" + errors +
                '}';
    }
}

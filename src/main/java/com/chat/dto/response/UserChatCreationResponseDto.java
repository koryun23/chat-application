package com.chat.dto.response;

import com.chat.entity.role.type.UserChatRoleType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserChatCreationResponseDto {

    private String creatorUsername;
    private Long userId;
    private Long chatId;
    private UserChatRoleType userChatRoleType;
    private LocalDateTime createdAt;

    private List<String> errors;

    public UserChatCreationResponseDto() {
    }

    public UserChatCreationResponseDto(String creatorUsername, Long userId, Long chatId, UserChatRoleType userChatRoleType, LocalDateTime createdAt) {
        this.creatorUsername = creatorUsername;
        this.userId = userId;
        this.chatId = chatId;
        this.userChatRoleType = userChatRoleType;
        this.createdAt = createdAt;
    }

    public UserChatCreationResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public UserChatRoleType getUserChatRoleType() {
        return userChatRoleType;
    }

    public void setUserChatRoleType(UserChatRoleType userChatRoleType) {
        this.userChatRoleType = userChatRoleType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
        UserChatCreationResponseDto that = (UserChatCreationResponseDto) o;
        return Objects.equals(creatorUsername, that.creatorUsername) && Objects.equals(userId, that.userId) && Objects.equals(chatId, that.chatId) && userChatRoleType == that.userChatRoleType && Objects.equals(createdAt, that.createdAt) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatorUsername, userId, chatId, userChatRoleType, createdAt, errors);
    }

    @Override
    public String toString() {
        return "UserChatCreationResponseDto{" +
                "creatorUsername='" + creatorUsername + '\'' +
                ", userId=" + userId +
                ", chatId=" + chatId +
                ", userChatRoleType=" + userChatRoleType +
                ", createdAt=" + createdAt +
                ", errors=" + errors +
                '}';
    }
}

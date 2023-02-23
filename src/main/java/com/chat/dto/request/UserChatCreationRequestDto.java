package com.chat.dto.request;

import com.chat.entity.role.type.UserChatRoleType;

import java.util.Objects;

public class UserChatCreationRequestDto {

    private String creatorUsername;
    private Long userId;
    private Long chatId;
    private UserChatRoleType userChatRoleType;

    public UserChatCreationRequestDto() {
    }

    public UserChatCreationRequestDto(String creatorUsername, Long userId, Long chatId, UserChatRoleType userChatRoleType) {
        this.creatorUsername = creatorUsername;
        this.userId = userId;
        this.chatId = chatId;
        this.userChatRoleType = userChatRoleType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChatCreationRequestDto that = (UserChatCreationRequestDto) o;
        return Objects.equals(creatorUsername, that.creatorUsername) && Objects.equals(userId, that.userId) && Objects.equals(chatId, that.chatId) && userChatRoleType == that.userChatRoleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatorUsername, userId, chatId, userChatRoleType);
    }

    @Override
    public String toString() {
        return "UserChatCreationRequestDto{" +
                "creatorUsername='" + creatorUsername + '\'' +
                ", userId=" + userId +
                ", chatId=" + chatId +
                ", userChatRoleType=" + userChatRoleType +
                '}';
    }
}

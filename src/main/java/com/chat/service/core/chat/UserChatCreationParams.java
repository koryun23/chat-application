package com.chat.service.core.chat;

import com.chat.entity.chat.UserChat;
import com.chat.entity.role.type.UserChatRoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Objects;

public class UserChatCreationParams {

    private Long userId;
    private Long chatId;
    private UserChatRoleType userChatRoleType;

    public UserChatCreationParams() {
    }

    public UserChatCreationParams(Long userId, Long chatId, UserChatRoleType userChatRoleType) {
        this.userId = userId;
        this.chatId = chatId;
        this.userChatRoleType = userChatRoleType;
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
        UserChatCreationParams that = (UserChatCreationParams) o;
        return Objects.equals(userId, that.userId) && Objects.equals(chatId, that.chatId) && userChatRoleType == that.userChatRoleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, chatId, userChatRoleType);
    }

    @Override
    public String toString() {
        return "UserChatCreationParams{" +
                "userId=" + userId +
                ", chatId=" + chatId +
                ", userChatRoleType=" + userChatRoleType +
                '}';
    }
}

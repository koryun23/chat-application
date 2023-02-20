package com.chat.service.core.chat;

import com.chat.entity.chat.type.ChatType;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChatCreationParams {

    private String name;
    private ChatType chatType;
    private LocalDateTime createdAt;

    public ChatCreationParams() {
    }

    public ChatCreationParams(String name, ChatType chatType, LocalDateTime createdAt) {
        this.name = name;
        this.chatType = chatType;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatCreationParams that = (ChatCreationParams) o;
        return Objects.equals(name, that.name) && chatType == that.chatType && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, chatType, createdAt);
    }

    @Override
    public String toString() {
        return "ChatCreationParams{" +
                "name='" + name + '\'' +
                ", chatType=" + chatType +
                ", createdAt=" + createdAt +
                '}';
    }
}

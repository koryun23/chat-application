package com.chat.dto.plain;

import com.chat.entity.chat.type.ChatType;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChatDto {

    private Long chatId;
    private String name;
    private ChatType chatType;
    private LocalDateTime createdAt;

    public ChatDto() {
    }

    public ChatDto(Long chatId, String name, ChatType chatType, LocalDateTime createdAt) {
        this.chatId = chatId;
        this.name = name;
        this.chatType = chatType;
        this.createdAt = createdAt;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
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
        ChatDto chatDto = (ChatDto) o;
        return Objects.equals(chatId, chatDto.chatId) && Objects.equals(name, chatDto.name) && chatType == chatDto.chatType && Objects.equals(createdAt, chatDto.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, name, chatType, createdAt);
    }

    @Override
    public String toString() {
        return "ChatDto{" +
                "chatId='" + chatId + '\'' +
                "name='" + name + '\'' +
                ", chatType=" + chatType +
                ", createdAt=" + createdAt +
                '}';
    }
}

package com.chat.dto.response;

import com.chat.entity.chat.type.ChatType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ChatCreationResponseDto {

    private Long id;
    private String name;
    private ChatType chatType;
    private LocalDateTime createdAt;
    private String creatorUsername;

    private List<String> errors;

    public ChatCreationResponseDto(Long id, String name, ChatType chatType, LocalDateTime createdAt, String creatorUsername) {
        this.id = id;
        this.name = name;
        this.chatType = chatType;
        this.createdAt = createdAt;
        this.creatorUsername = creatorUsername;
    }

    public ChatCreationResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatCreationResponseDto that = (ChatCreationResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && chatType == that.chatType && Objects.equals(createdAt, that.createdAt) && Objects.equals(errors, that.errors) && Objects.equals(creatorUsername, that.creatorUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, chatType, createdAt, errors, creatorUsername);
    }

    @Override
    public String toString() {
        return "ChatCreationResponseDto{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", chatType=" + chatType +
                ", createdAt=" + createdAt +
                ", creatorUsername=" + creatorUsername +
                ", errors=" + errors +
                '}';
    }
}

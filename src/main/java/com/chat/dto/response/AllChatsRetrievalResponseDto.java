package com.chat.dto.response;

import com.chat.dto.plain.ChatDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class AllChatsRetrievalResponseDto {

    private Long userId;
    private List<ChatDto> chatDtos;
    private LocalDateTime retrievedAt;

    private List<String> errors;

    public AllChatsRetrievalResponseDto() {
    }

    public AllChatsRetrievalResponseDto(Long userId, List<ChatDto> chats, LocalDateTime retrievedAt) {
        this.userId = userId;
        this.chatDtos = chats;
        this.retrievedAt = retrievedAt;
    }

    public AllChatsRetrievalResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ChatDto> getChatDtos() {
        return chatDtos;
    }

    public void setChatDtos(List<ChatDto> chats) {
        this.chatDtos = chats;
    }

    public LocalDateTime getRetrievedAt() {
        return retrievedAt;
    }

    public void setRetrievedAt(LocalDateTime retrievedAt) {
        this.retrievedAt = retrievedAt;
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
        AllChatsRetrievalResponseDto that = (AllChatsRetrievalResponseDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(chatDtos, that.chatDtos) && Objects.equals(retrievedAt, that.retrievedAt) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, chatDtos, retrievedAt, errors);
    }

    @Override
    public String toString() {
        return "ChatListRetrievalResponseDto{" +
                "userId=" + userId +
                ", chats=" + chatDtos +
                ", retrievedAt=" + retrievedAt +
                ", errors=" + errors +
                '}';
    }
}

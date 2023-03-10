package com.chat.dto.request;

import java.util.Objects;

public class ChatListRetrievalRequestDto {

    private String retrieverUsername;
    private Long userId;

    public ChatListRetrievalRequestDto() {
    }

    public ChatListRetrievalRequestDto(String retrieverUsername, Long userId) {
        this.retrieverUsername = retrieverUsername;
        this.userId = userId;
    }

    public String getRetrieverUsername() {
        return retrieverUsername;
    }

    public void setRetrieverUsername(String retrieverUsername) {
        this.retrieverUsername = retrieverUsername;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatListRetrievalRequestDto that = (ChatListRetrievalRequestDto) o;
        return Objects.equals(retrieverUsername, that.retrieverUsername) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retrieverUsername, userId);
    }

    @Override
    public String toString() {
        return "ChatListRetrievalRequestDto{" +
                "retrieverUsername='" + retrieverUsername + '\'' +
                ", userId=" + userId +
                '}';
    }
}

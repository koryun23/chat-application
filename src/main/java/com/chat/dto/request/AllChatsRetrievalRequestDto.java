package com.chat.dto.request;

import java.util.Objects;

public class AllChatsRetrievalRequestDto {

    private String retrieverUsername;

    public AllChatsRetrievalRequestDto() {
    }

    public AllChatsRetrievalRequestDto(String retrieverUsername, Long userId) {
        this.retrieverUsername = retrieverUsername;
    }

    public String getRetrieverUsername() {
        return retrieverUsername;
    }

    public void setRetrieverUsername(String retrieverUsername) {
        this.retrieverUsername = retrieverUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllChatsRetrievalRequestDto that = (AllChatsRetrievalRequestDto) o;
        return Objects.equals(retrieverUsername, that.retrieverUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retrieverUsername);
    }

    @Override
    public String toString() {
        return "ChatListRetrievalRequestDto{" +
                "retrieverUsername='" + retrieverUsername + '\'' +
                '}';
    }
}

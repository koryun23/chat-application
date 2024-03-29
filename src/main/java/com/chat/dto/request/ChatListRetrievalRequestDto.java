package com.chat.dto.request;

import java.util.Objects;

public class ChatListRetrievalRequestDto {

    private String retrieverUsername;
    private String keyWord;

    public ChatListRetrievalRequestDto() {
    }

    public ChatListRetrievalRequestDto(String retrieverUsername, String keyWord) {
        this.retrieverUsername = retrieverUsername;
        this.keyWord = keyWord;
    }

    public String getRetrieverUsername() {
        return retrieverUsername;
    }

    public void setRetrieverUsername(String retrieverUsername) {
        this.retrieverUsername = retrieverUsername;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatListRetrievalRequestDto that = (ChatListRetrievalRequestDto) o;
        return Objects.equals(retrieverUsername, that.retrieverUsername) && Objects.equals(keyWord, that.keyWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retrieverUsername, keyWord);
    }

    @Override
    public String toString() {
        return "ChatListRetrievalRequestDto{" +
                "retrieverUsername='" + retrieverUsername + '\'' +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}

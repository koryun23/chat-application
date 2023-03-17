package com.chat.dto.response;

import com.chat.dto.plain.ChatDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ChatListRetrievalResponseDto {

    private String retrieverUsername;
    private String keyWord;
    private List<ChatDto> chatDtoList;
    private LocalDateTime retrievedAt;

    private List<String> errors;

    public ChatListRetrievalResponseDto() {
    }

    public ChatListRetrievalResponseDto(String retrieverUsername, String keyWord, List<ChatDto> chatDtoList, LocalDateTime retrievedAt) {
        this.retrieverUsername = retrieverUsername;
        this.keyWord = keyWord;
        this.chatDtoList = chatDtoList;
        this.retrievedAt = retrievedAt;
    }

    public ChatListRetrievalResponseDto(List<String> errors) {
        this.errors = errors;
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

    public List<ChatDto> getChatDtoList() {
        return chatDtoList;
    }

    public void setChatDtoList(List<ChatDto> chatDtoList) {
        this.chatDtoList = chatDtoList;
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
        ChatListRetrievalResponseDto that = (ChatListRetrievalResponseDto) o;
        return Objects.equals(retrieverUsername, that.retrieverUsername) && Objects.equals(keyWord, that.keyWord) && Objects.equals(chatDtoList, that.chatDtoList) && Objects.equals(retrievedAt, that.retrievedAt) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retrieverUsername, keyWord, chatDtoList, retrievedAt, errors);
    }

    @Override
    public String toString() {
        return "ChatListRetrievalResponseDto{" +
                "retrieverUsername='" + retrieverUsername + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", chatDtoList=" + chatDtoList +
                ", retrievedAt=" + retrievedAt +
                ", errors=" + errors +
                '}';
    }
}

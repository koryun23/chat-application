package com.chat.dto.response;

import com.chat.dto.plain.MessageDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class MessagesInChatListRetrievalResponseDto {

    private String retrieverUsername;
    private Long chatId;
    private List<MessageDto> messageDtoList;
    private LocalDateTime retrievedAt;

    private List<String> errors;

    public MessagesInChatListRetrievalResponseDto() {
    }

    public MessagesInChatListRetrievalResponseDto(String retrieverUsername, Long chatId, List<MessageDto> messageDtoList, LocalDateTime retrievedAt) {
        this.retrieverUsername = retrieverUsername;
        this.chatId = chatId;
        this.messageDtoList = messageDtoList;
        this.retrievedAt = retrievedAt;
    }

    public MessagesInChatListRetrievalResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public String getRetrieverUsername() {
        return retrieverUsername;
    }

    public void setRetrieverUsername(String retrieverUsername) {
        this.retrieverUsername = retrieverUsername;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public List<MessageDto> getMessageDtoList() {
        return messageDtoList;
    }

    public void setMessageDtoList(List<MessageDto> messageDtoList) {
        this.messageDtoList = messageDtoList;
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
        MessagesInChatListRetrievalResponseDto that = (MessagesInChatListRetrievalResponseDto) o;
        return Objects.equals(retrieverUsername, that.retrieverUsername) && Objects.equals(chatId, that.chatId) && Objects.equals(messageDtoList, that.messageDtoList) && Objects.equals(retrievedAt, that.retrievedAt) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retrieverUsername, chatId, messageDtoList, retrievedAt, errors);
    }

    @Override
    public String toString() {
        return "MessagesInChatListRetrievalResponseDto{" +
                "retrieverUsername='" + retrieverUsername + '\'' +
                ", chatId=" + chatId +
                ", messageDtoList=" + messageDtoList +
                ", retrievedAt=" + retrievedAt +
                ", errors=" + errors +
                '}';
    }
}

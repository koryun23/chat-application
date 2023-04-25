package com.chat.dto.request;

import java.util.Objects;

public class MessagesInChatListRetrievalRequestDto {

    private String retrieverUsername;
    private Long chatId;

    public MessagesInChatListRetrievalRequestDto() {
    }

    public MessagesInChatListRetrievalRequestDto(String retrieverUsername, Long chatId) {
        this.retrieverUsername = retrieverUsername;
        this.chatId = chatId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesInChatListRetrievalRequestDto that = (MessagesInChatListRetrievalRequestDto) o;
        return Objects.equals(retrieverUsername, that.retrieverUsername) && Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retrieverUsername, chatId);
    }

    @Override
    public String toString() {
        return "MessagesInChatListRetrievalRequestDto{" +
                "retrieverUsername='" + retrieverUsername + '\'' +
                ", chatId=" + chatId +
                '}';
    }
}

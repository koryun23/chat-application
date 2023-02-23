package com.chat.dto.request;

import java.util.Objects;

public class ChatDeletionRequestDto {

    private Long chatId;
    private String chatDeleterUsername;

    public ChatDeletionRequestDto() {
    }

    public ChatDeletionRequestDto(Long chatId, String chatDeleterUsername) {
        this.chatId = chatId;
        this.chatDeleterUsername = chatDeleterUsername;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getChatDeleterUsername() {
        return chatDeleterUsername;
    }

    public void setChatDeleterUsername(String chatDeleterUsername) {
        this.chatDeleterUsername = chatDeleterUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatDeletionRequestDto that = (ChatDeletionRequestDto) o;
        return Objects.equals(chatId, that.chatId) && Objects.equals(chatDeleterUsername, that.chatDeleterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, chatDeleterUsername);
    }

    @Override
    public String toString() {
        return "ChatDeletionRequestDto{" +
                "chatId=" + chatId +
                "chatDeleterUsername=" + chatDeleterUsername +
                '}';
    }
}

package com.chat.dto.plain;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageDto {

    private String message;
    private String username;
    private Long chatId;
    private LocalDateTime sentAt;

    public MessageDto() {
    }

    public MessageDto(String message, String username, Long chatId, LocalDateTime sentAt) {
        this.message = message;
        this.username = username;
        this.chatId = chatId;
        this.sentAt = sentAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDto that = (MessageDto) o;
        return Objects.equals(message, that.message) && Objects.equals(username, that.username) && Objects.equals(chatId, that.chatId) && Objects.equals(sentAt, that.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, username, chatId, sentAt);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "message='" + message + '\'' +
                ", username='" + username + '\'' +
                ", chatId=" + chatId +
                ", sentAt=" + sentAt +
                '}';
    }
}

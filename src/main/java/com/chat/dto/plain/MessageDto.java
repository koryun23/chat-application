package com.chat.dto.plain;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageDto {

    private String message;
    private String sentBy;
    private String chatName;
    private LocalDateTime sentAt;

    public MessageDto() {
    }

    public MessageDto(String message, String sentBy, String chatName, LocalDateTime sentAt) {
        this.message = message;
        this.sentBy = sentBy;
        this.chatName = chatName;
        this.sentAt = sentAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
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
        return Objects.equals(message, that.message) && Objects.equals(sentBy, that.sentBy) && Objects.equals(chatName, that.chatName) && Objects.equals(sentAt, that.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sentBy, chatName, sentAt);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "message='" + message + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", chatName=" + chatName +
                ", sentAt=" + sentAt +
                '}';
    }
}

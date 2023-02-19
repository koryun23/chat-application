package com.chat.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class SendPublicMessageResponseDto {

    private String message;
    private String sentBy;
    private String chat;
    private LocalDateTime sentAt;

    private List<String> errors;

    public SendPublicMessageResponseDto() {
    }

    public SendPublicMessageResponseDto(String message, String sentBy, String chat, LocalDateTime sentAt) {
        this.message = message;
        this.sentBy = sentBy;
        this.chat = chat;
        this.sentAt = sentAt;
    }

    public SendPublicMessageResponseDto(List<String> errors) {
        this.errors = errors;
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

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
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
        SendPublicMessageResponseDto that = (SendPublicMessageResponseDto) o;
        return Objects.equals(message, that.message) && Objects.equals(sentBy, that.sentBy) && Objects.equals(chat, that.chat) && Objects.equals(sentAt, that.sentAt) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sentBy, chat, sentAt, errors);
    }

    @Override
    public String toString() {
        return "SendPublicMessageResponseDto{" +
                "message='" + message + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", chat='" + chat + '\'' +
                ", sentAt=" + sentAt +
                ", errors=" + errors +
                '}';
    }
}

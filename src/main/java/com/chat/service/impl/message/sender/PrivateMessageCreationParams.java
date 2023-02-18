package com.chat.service.impl.message.sender;

import java.time.LocalDateTime;
import java.util.Objects;

public class PrivateMessageCreationParams {

    private String message;
    private String sentTo;
    private String sentBy;
    private LocalDateTime sentAt;

    public PrivateMessageCreationParams() {
    }

    public PrivateMessageCreationParams(String message, String sentTo, String sentBy, LocalDateTime sentAt) {
        this.message = message;
        this.sentTo = sentTo;
        this.sentBy = sentBy;
        this.sentAt = sentAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public String getSentBy() {
        return sentBy;
    }

    public void setSentBy(String sentBy) {
        this.sentBy = sentBy;
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
        PrivateMessageCreationParams that = (PrivateMessageCreationParams) o;
        return Objects.equals(message, that.message) && Objects.equals(sentTo, that.sentTo) && Objects.equals(sentBy, that.sentBy) && Objects.equals(sentAt, that.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sentTo, sentBy, sentAt);
    }

    @Override
    public String toString() {
        return "MessageCreationParams{" +
                "message='" + message + '\'' +
                ", sentTo='" + sentTo + '\'' +
                ", sentBy='" + sentBy + '\'' +
                '}';
    }
}

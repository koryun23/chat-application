package com.chat.service.core.message.persist;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageCreationParams {
    private String body;
    private LocalDateTime sentAt;

    public MessageCreationParams() {
    }

    public MessageCreationParams(String body, LocalDateTime sentAt) {
        this.body = body;
        this.sentAt = sentAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
        MessageCreationParams that = (MessageCreationParams) o;
        return Objects.equals(body, that.body) && Objects.equals(sentAt, that.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, sentAt);
    }

    @Override
    public String toString() {
        return "MessageCreationParams{" +
                "body='" + body + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}

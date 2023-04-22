package com.chat.service.core.message.persist;

import java.time.LocalDateTime;
import java.util.Objects;

public class MessageCreationParams {

    private String body;
    private String sentBy;
    private LocalDateTime sentAt;

    public MessageCreationParams() {
    }

    public MessageCreationParams(String body, String sentBy, LocalDateTime sentAt) {
        this.body = body;
        this.sentBy = sentBy;
        this.sentAt = sentAt;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
        MessageCreationParams that = (MessageCreationParams) o;
        return Objects.equals(body, that.body) && Objects.equals(sentBy, that.sentBy) && Objects.equals(sentAt, that.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(body, sentBy, sentAt);
    }

    @Override
    public String toString() {
        return "MessageCreationParams{" +
                "body='" + body + '\'' +
                "body='" + sentBy + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}

package com.chat.dto.response;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class SendPrivateMessageResponseDto {

    private String message;
    private String sentTo;
    private String sentBy;
    private LocalDateTime sentAt;
    private List<String> errors;

    public SendPrivateMessageResponseDto() {
    }

    public SendPrivateMessageResponseDto(String message, String sentTo, String sentBy, LocalDateTime sentAt) {
        this.message = message;
        this.sentTo = sentTo;
        this.sentBy = sentBy;
        this.sentAt = sentAt;
    }

    public SendPrivateMessageResponseDto(List<String> errors) {
        this.errors = errors;
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
        SendPrivateMessageResponseDto that = (SendPrivateMessageResponseDto) o;
        return Objects.equals(message, that.message) && Objects.equals(sentTo, that.sentTo) && Objects.equals(sentBy, that.sentBy) && Objects.equals(sentAt, that.sentAt) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sentTo, sentBy, sentAt, errors);
    }

    @Override
    public String toString() {
        return "SendPrivateMessageResponseDto{" +
                "message='" + message + '\'' +
                ", sentTo='" + sentTo + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", sentAt=" + sentAt +
                ", errors=" + errors +
                '}';
    }
}

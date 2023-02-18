package com.chat.dto.request;

import java.util.Objects;

public class SendPrivateMessageRequestDto {
    private String message;
    private String sentTo;
    private String sentBy;

    public SendPrivateMessageRequestDto() {
    }

    public SendPrivateMessageRequestDto(String message, String sentTo, String sentBy) {
        this.message = message;
        this.sentTo = sentTo;
        this.sentBy = sentBy;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendPrivateMessageRequestDto that = (SendPrivateMessageRequestDto) o;
        return Objects.equals(message, that.message) && Objects.equals(sentTo, that.sentTo) && Objects.equals(sentBy, that.sentBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sentTo, sentBy);
    }

    @Override
    public String toString() {
        return "SendPrivateMessageRequestDto{" +
                "message='" + message + '\'' +
                ", sentTo='" + sentTo + '\'' +
                ", sentBy='" + sentBy + '\'' +
                '}';
    }
}

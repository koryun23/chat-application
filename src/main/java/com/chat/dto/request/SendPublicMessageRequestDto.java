package com.chat.dto.request;

import java.util.Objects;

public class SendPublicMessageRequestDto {

    private String message;
    private String sentBy;
    private String chat;

    public SendPublicMessageRequestDto() {
    }

    public SendPublicMessageRequestDto(String message, String sentBy, String chat) {
        this.message = message;
        this.sentBy = sentBy;
        this.chat = chat;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendPublicMessageRequestDto that = (SendPublicMessageRequestDto) o;
        return Objects.equals(message, that.message) && Objects.equals(sentBy, that.sentBy) && Objects.equals(chat, that.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sentBy, chat);
    }

    @Override
    public String toString() {
        return "SendPublicMessageRequestDto{" +
                "message='" + message + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", chat='" + chat + '\'' +
                '}';
    }
}

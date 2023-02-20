package com.chat.service.core.message.sender;

import java.util.Objects;

public class PublicMessageCreationParams {

    private String message;
    private String chatName;
    private String sentBy;

    public PublicMessageCreationParams() {
    }

    public PublicMessageCreationParams(String message, String chatName, String sentBy) {
        this.message = message;
        this.chatName = chatName;
        this.sentBy = sentBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
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
        PublicMessageCreationParams that = (PublicMessageCreationParams) o;
        return Objects.equals(message, that.message) && Objects.equals(chatName, that.chatName) && Objects.equals(sentBy, that.sentBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, chatName, sentBy);
    }

    @Override
    public String toString() {
        return "PublicMessageCreationParams{" +
                "message='" + message + '\'' +
                ", chatName='" + chatName + '\'' +
                ", sentBy='" + sentBy + '\'' +
                '}';
    }
}

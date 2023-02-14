package com.chat.service.message.sender;

import java.util.Objects;

public class Message {

    private String message;
    private String destinationUser;

    public Message() {
    }

    public Message(String message, String destinationUser) {
        this.message = message;
        this.destinationUser = destinationUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(String destinationUser) {
        this.destinationUser = destinationUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message that = (Message) o;
        return Objects.equals(message, that.message) && Objects.equals(destinationUser, that.destinationUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, destinationUser);
    }

    @Override
    public String toString() {
        return "SendMessageRequestDto{" +
                "message='" + message + '\'' +
                ", destinationUser='" + destinationUser + '\'' +
                '}';
    }
}

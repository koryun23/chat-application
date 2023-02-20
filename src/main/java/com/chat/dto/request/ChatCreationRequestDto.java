package com.chat.dto.request;

import com.chat.entity.chat.type.ChatType;

import java.util.Objects;

public class ChatCreationRequestDto {

    private String name;
    private ChatType chatType;
    private String creatorUsername;

    public ChatCreationRequestDto(String name, ChatType chatType, String creator) {
        this.name = name;
        this.chatType = chatType;
        this.creatorUsername = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatCreationRequestDto that = (ChatCreationRequestDto) o;
        return Objects.equals(name, that.name) && chatType == that.chatType && Objects.equals(creatorUsername, that.creatorUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, chatType, creatorUsername);
    }

    @Override
    public String toString() {
        return "ChatCreationRequestDto{" +
                "name='" + name + '\'' +
                ", chatType=" + chatType +
                ", creator=" + creatorUsername +
                '}';
    }
}

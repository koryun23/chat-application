package com.chat.dto.request;

import java.util.Objects;

public class ChatUpdateRequestDto {

    private Long chatId;
    private String name;
    private String updaterUsername;

    public ChatUpdateRequestDto() {
    }

    public ChatUpdateRequestDto(Long chatId, String name, String updaterUsername) {
        this.chatId = chatId;
        this.name = name;
        this.updaterUsername = updaterUsername;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdaterUsername() {
        return updaterUsername;
    }

    public void setUpdaterUsername(String updaterUsername) {
        this.updaterUsername = updaterUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatUpdateRequestDto that = (ChatUpdateRequestDto) o;
        return Objects.equals(chatId, that.chatId) && Objects.equals(name, that.name) && Objects.equals(updaterUsername, that.updaterUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, name, updaterUsername);
    }

    @Override
    public String toString() {
        return "ChatUpdateRequestDto{" +
                "chatId=" + chatId +
                ", name='" + name + '\'' +
                ", updaterUsername='" + updaterUsername + '\'' +
                '}';
    }
}

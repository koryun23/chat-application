package com.chat.dto.request;

import java.util.Objects;

public class ChatUpdateRequestDto {

    private Long chatId;
    private String name;

    public ChatUpdateRequestDto() {
    }

    public ChatUpdateRequestDto(Long chatId, String name) {
        this.chatId = chatId;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatUpdateRequestDto that = (ChatUpdateRequestDto) o;
        return Objects.equals(chatId, that.chatId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, name);
    }

    @Override
    public String toString() {
        return "ChatUpdateRequestDto{" +
                "chatId=" + chatId +
                ", name='" + name + '\'' +
                '}';
    }
}

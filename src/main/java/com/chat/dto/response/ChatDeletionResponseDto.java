package com.chat.dto.response;

import java.util.List;
import java.util.Objects;

public class ChatDeletionResponseDto {

    private Long chatId;

    private List<String> errors;

    public ChatDeletionResponseDto() {
    }

    public ChatDeletionResponseDto(Long chatId) {
        this.chatId = chatId;
    }

    public ChatDeletionResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
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
        ChatDeletionResponseDto that = (ChatDeletionResponseDto) o;
        return Objects.equals(chatId, that.chatId) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, errors);
    }

    @Override
    public String toString() {
        return "ChatDeletionResponseDto{" +
                "chatId=" + chatId +
                "errors=" + errors +
                '}';
    }
}

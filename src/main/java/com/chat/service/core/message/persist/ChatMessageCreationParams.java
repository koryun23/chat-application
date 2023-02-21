package com.chat.service.core.message.persist;

import java.util.Objects;

public class ChatMessageCreationParams {

    private Long chatId;
    private Long messageId;

    public ChatMessageCreationParams() {
    }

    public ChatMessageCreationParams(Long chatId, Long messageId) {
        this.chatId = chatId;
        this.messageId = messageId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessageCreationParams that = (ChatMessageCreationParams) o;
        return Objects.equals(chatId, that.chatId) && Objects.equals(messageId, that.messageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, messageId);
    }

    @Override
    public String toString() {
        return "ChatMessageCreationParams{" +
                "chatId=" + chatId +
                ", messageId=" + messageId +
                '}';
    }
}

package com.chat.dto.request;

import java.util.Objects;

public class UserChatRetrievalRequestDto {

    private String requestingUsername;
    private Long chatId;

    public UserChatRetrievalRequestDto() {
    }

    public UserChatRetrievalRequestDto(String requestingUsername, Long chatId) {
        this.requestingUsername = requestingUsername;
        this.chatId = chatId;
    }

    public String getRequestingUsername() {
        return requestingUsername;
    }

    public void setRequestingUsername(String requestingUsername) {
        this.requestingUsername = requestingUsername;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChatRetrievalRequestDto that = (UserChatRetrievalRequestDto) o;
        return Objects.equals(requestingUsername, that.requestingUsername) && Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestingUsername, chatId);
    }

    @Override
    public String toString() {
        return "UserChatRetrievalRequestDto{" +
                "requestingUsername='" + requestingUsername + '\'' +
                ", chatId=" + chatId +
                '}';
    }
}

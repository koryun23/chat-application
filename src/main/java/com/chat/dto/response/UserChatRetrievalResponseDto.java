package com.chat.dto.response;

import com.chat.service.core.chat.UserChatCreationParams;
import com.chat.service.core.user.UserCreationParams;

import java.util.List;
import java.util.Objects;

public class UserChatRetrievalResponseDto {

    private String requestingUsername;
    private Long chatId;
    private List<UserChatCreationParams> users;

    private List<String> errors;

    public UserChatRetrievalResponseDto() {
    }

    public UserChatRetrievalResponseDto(String requestingUsername, Long chatId, List<UserChatCreationParams> users) {
        this.requestingUsername = requestingUsername;
        this.chatId = chatId;
        this.users = users;
    }

    public UserChatRetrievalResponseDto(List<String> errors) {
        this.errors = errors;
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

    public List<UserChatCreationParams> getUsers() {
        return users;
    }

    public void setUsers(List<UserChatCreationParams> users) {
        this.users = users;
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
        UserChatRetrievalResponseDto that = (UserChatRetrievalResponseDto) o;
        return Objects.equals(requestingUsername, that.requestingUsername) && Objects.equals(chatId, that.chatId) && Objects.equals(users, that.users) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestingUsername, chatId, users, errors);
    }

    @Override
    public String toString() {
        return "UserChatRetrievalResponseDto{" +
                "requestingUsername='" + requestingUsername + '\'' +
                ", chatId=" + chatId +
                ", users=" + users +
                ", errors=" + errors +
                '}';
    }
}

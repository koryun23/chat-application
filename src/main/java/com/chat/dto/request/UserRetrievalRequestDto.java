package com.chat.dto.request;

import java.util.Objects;

public class UserRetrievalRequestDto {

    private String retrieverUsername;
    private String username;

    public UserRetrievalRequestDto() {
    }

    public UserRetrievalRequestDto(String retrieverUsername, String username) {
        this.retrieverUsername = retrieverUsername;
        this.username = username;
    }

    public String getRetrieverUsername() {
        return retrieverUsername;
    }

    public void setRetrieverUsername(String retrieverUsername) {
        this.retrieverUsername = retrieverUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRetrievalRequestDto that = (UserRetrievalRequestDto) o;
        return Objects.equals(retrieverUsername, that.retrieverUsername) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(retrieverUsername, username);
    }

    @Override
    public String toString() {
        return "UserRetrievalRequestDto{" +
                "retrieverUsername='" + retrieverUsername + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

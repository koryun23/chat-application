package com.chat.dto.response;

import com.chat.dto.plain.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserListRetrievalResponseDto {

    private List<UserDto> userDtoList;
    private LocalDateTime retrievedAt;

    private List<String> errors;

    public UserListRetrievalResponseDto(List<UserDto> userDtoList, LocalDateTime retrievedAt) {
        this.userDtoList = userDtoList;
        this.retrievedAt = retrievedAt;
    }

    public UserListRetrievalResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public UserListRetrievalResponseDto() {
    }

    public List<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public LocalDateTime getRetrievedAt() {
        return retrievedAt;
    }

    public void setRetrievedAt(LocalDateTime retrievedAt) {
        this.retrievedAt = retrievedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserListRetrievalResponseDto that = (UserListRetrievalResponseDto) o;
        return Objects.equals(userDtoList, that.userDtoList) && Objects.equals(errors, that.errors) && Objects.equals(retrievedAt, that.retrievedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDtoList, retrievedAt, errors);
    }

    @Override
    public String toString() {
        return "UserListRetrievalResponseDto{" +
                "userDtoList=" + userDtoList +
                "retrievedAt=" + retrievedAt +
                ", errors=" + errors +
                '}';
    }
}

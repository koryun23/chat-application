package com.chat.dto.response;

import com.chat.dto.plain.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserRetrievalResponseDto {

    private UserDto userDto;
    private LocalDateTime retrievedAt;

    private List<String> errors;

    public UserRetrievalResponseDto() {
    }

    public UserRetrievalResponseDto(UserDto userDto, LocalDateTime retrievedAt) {
        this.userDto = userDto;
        this.retrievedAt = retrievedAt;
    }

    public UserRetrievalResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
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
        UserRetrievalResponseDto that = (UserRetrievalResponseDto) o;
        return Objects.equals(userDto, that.userDto) && Objects.equals(errors, that.errors) && Objects.equals(retrievedAt, that.retrievedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userDto, retrievedAt, errors);
    }

    @Override
    public String toString() {
        return "UserRetrievalResponseDto{" +
                "userDto=" + userDto +
                "retrievedAt=" + retrievedAt +
                ", errors=" + errors +
                '}';
    }
}

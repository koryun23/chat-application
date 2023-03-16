package com.chat.dto.plain;

import java.time.LocalDateTime;
import java.util.Objects;

public class UserDto {

    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private LocalDateTime joinedAt;

    public UserDto() {
    }

    public UserDto(String username, String password, String firstName, String secondName, LocalDateTime joinedAt) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.joinedAt = joinedAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(username, userDto.username) && Objects.equals(password, userDto.password) && Objects.equals(firstName, userDto.firstName) && Objects.equals(secondName, userDto.secondName) && Objects.equals(joinedAt, userDto.joinedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, secondName, joinedAt);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", joinedAt=" + joinedAt +
                '}';
    }
}

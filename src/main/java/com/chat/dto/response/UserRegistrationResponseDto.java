package com.chat.dto.response;

import com.chat.entity.role.type.UserAppRoleType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class UserRegistrationResponseDto {

    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private UserAppRoleType userAppRoleType;
    private LocalDateTime registeredAt;

    private List<String> errors;

    public UserRegistrationResponseDto() {
    }

    public UserRegistrationResponseDto(String username, String password, String firstName, String secondName, UserAppRoleType userAppRoleType, LocalDateTime registeredAt) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userAppRoleType = userAppRoleType;
        this.registeredAt = registeredAt;
    }

    public UserRegistrationResponseDto(List<String> errors) {
        this.errors = errors;
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

    public UserAppRoleType getUserAppRoleType() {
        return userAppRoleType;
    }

    public void setUserAppRoleType(UserAppRoleType userAppRoleType) {
        this.userAppRoleType = userAppRoleType;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
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
        UserRegistrationResponseDto that = (UserRegistrationResponseDto) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && userAppRoleType == that.userAppRoleType && Objects.equals(registeredAt, that.registeredAt) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, secondName, userAppRoleType, registeredAt, errors);
    }

    @Override
    public String toString() {
        return "UserRegistrationResponseDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", userAppRoleType=" + userAppRoleType +
                ", registeredAt=" + registeredAt +
                ", errors=" + errors +
                '}';
    }
}

package com.chat.dto.request;

import com.chat.entity.role.type.UserAppRoleType;

import java.util.List;
import java.util.Objects;

public class UserRegistrationRequestDto {

    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private UserAppRoleType userAppRoleType;

    public UserRegistrationRequestDto() {
    }

    public UserRegistrationRequestDto(String username, String password, String firstName, String secondName, UserAppRoleType userAppRoleType) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userAppRoleType = userAppRoleType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrationRequestDto that = (UserRegistrationRequestDto) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && userAppRoleType == that.userAppRoleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, secondName, userAppRoleType);
    }

    @Override
    public String toString() {
        return "UserRegistrationRequestDto{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", userAppRoleType=" + userAppRoleType +
                '}';
    }
}

package com.chat.dto.response;

import com.chat.entity.role.type.UserAppRoleType;

import java.util.List;
import java.util.Objects;

public class SuccessfulAuthenticationResponseDto {

    private String token;
    private String username;
    private String password;
    private String firstName;
    private String secondName;
    private List<UserAppRoleType> userAppRoleTypeList;

    public SuccessfulAuthenticationResponseDto() {
    }

    public SuccessfulAuthenticationResponseDto(String token, String username, String password, String firstName, String secondName, List<UserAppRoleType> userAppRoleTypeList) {
        this.token = token;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.userAppRoleTypeList = userAppRoleTypeList;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public List<UserAppRoleType> getUserAppRoleTypeList() {
        return userAppRoleTypeList;
    }

    public void setUserAppRoleTypeList(List<UserAppRoleType> userAppRoleTypeList) {
        this.userAppRoleTypeList = userAppRoleTypeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuccessfulAuthenticationResponseDto that = (SuccessfulAuthenticationResponseDto) o;
        return Objects.equals(token, that.token) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(firstName, that.firstName) && Objects.equals(secondName, that.secondName) && Objects.equals(userAppRoleTypeList, that.userAppRoleTypeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, username, password, firstName, secondName, userAppRoleTypeList);
    }

    @Override
    public String toString() {
        return "SuccessfulAuthenticationResponseDto{" +
                "token='" + token + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", userAppRoleTypeList=" + userAppRoleTypeList +
                '}';
    }
}

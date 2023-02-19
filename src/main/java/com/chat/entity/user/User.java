package com.chat.entity.user;

import com.chat.entity.chat.Chat;
import com.chat.entity.chat.UserChat;
import com.chat.entity.message.Message;
import com.chat.entity.message.UserMessage;
import com.chat.entity.role.UserAppRole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQUENCE")
    @SequenceGenerator(name = "USER_SEQUENCE")
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "second_name", nullable = false)
    private String secondName;

    @Column(name = "joined_at", nullable = false)
    private LocalDateTime joinedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserChat> userChats;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private UserAppRole userAppRole;

    @OneToMany(mappedBy = "sentTo", cascade = CascadeType.PERSIST)
    private List<UserMessage> messagesSentToUser;

    @OneToMany(mappedBy = "sentBy", cascade = CascadeType.PERSIST)
    private List<Message> messagesSentByUser;

    public List<UserMessage> getMessagesSentToUser() {
        return messagesSentToUser;
    }

    public void setMessagesSentToUser(List<UserMessage> messagesSentToUser) {
        this.messagesSentToUser = messagesSentToUser;
    }

    public List<Message> getMessagesSentByUser() {
        return messagesSentByUser;
    }

    public void setMessagesSentByUser(List<Message> messagesSentByUser) {
        this.messagesSentByUser = messagesSentByUser;
    }

    public User(String username, String password, String firstName, String secondName, LocalDateTime joinedAt) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.secondName = secondName;
        this.joinedAt = joinedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<UserChat> getUserChats() {
        return userChats;
    }

    public void setUserChats(List<UserChat> userChats) {
        this.userChats = userChats;
    }

    public UserAppRole getUserAppRole() {
        return userAppRole;
    }

    public void setUserAppRole(UserAppRole userAppRole) {
        this.userAppRole = userAppRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(secondName, user.secondName) && Objects.equals(joinedAt, user.joinedAt) && Objects.equals(userChats, user.userChats) && Objects.equals(userAppRole, user.userAppRole) && Objects.equals(messagesSentToUser, user.messagesSentToUser) && Objects.equals(messagesSentByUser, user.messagesSentByUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstName, secondName, joinedAt, userChats, userAppRole, messagesSentToUser, messagesSentByUser);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", joinedAt=" + joinedAt +
                ", userChats=" + userChats +
                ", userAppRole=" + userAppRole +
                ", messagesSentToUser=" + messagesSentToUser +
                ", messagesSentByUser=" + messagesSentByUser +
                '}';
    }
}
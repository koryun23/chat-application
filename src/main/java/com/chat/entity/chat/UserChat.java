package com.chat.entity.chat;

import com.chat.entity.role.type.UserChatRoleType;
import com.chat.entity.user.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "USER_CHAT")
public class UserChat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_GROUP_CHAT_SEQUENCE")
    @SequenceGenerator(name = "USER_GROUP_CHAT_SEQUENCE")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id",
                foreignKey = @ForeignKey(name = "USER_CHAT_USER_USER_ID"),
                nullable = false,
                referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "chat_id",
                foreignKey = @ForeignKey(name = "USER_CHAT_CHAT_CHAT_ID"),
                nullable = false,
                referencedColumnName = "id")
    private Chat chat;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role", nullable = false, length = 40)
    private UserChatRoleType userChatRoleType;

    public UserChat() {
    }

    public UserChat(User user, Chat chat, UserChatRoleType userChatRoleType) {
        this.user = user;
        this.chat = chat;
        this.userChatRoleType = userChatRoleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public UserChatRoleType getUserChatRoleType() {
        return userChatRoleType;
    }

    public void setUserChatRoleType(UserChatRoleType userChatRoleType) {
        this.userChatRoleType = userChatRoleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserChat userChat = (UserChat) o;
        return Objects.equals(id, userChat.id) && Objects.equals(user, userChat.user) && Objects.equals(chat, userChat.chat) && userChatRoleType == userChat.userChatRoleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, chat, userChatRoleType);
    }

    @Override
    public String toString() {
        return "UserChat{" +
                "id=" + id +
                ", user=" + user +
                ", chat=" + chat +
                ", userChatRoleType=" + userChatRoleType +
                '}';
    }
}

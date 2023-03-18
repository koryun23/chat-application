package com.chat.entity.chat;

import com.chat.entity.chat.type.ChatType;
import com.chat.entity.message.ChatMessage;
import com.chat.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CHATS")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "CHAT_SEQUENCE")
    @SequenceGenerator(name = "CHAT_SEQUENCE")
    private Long id;

    @Column(name = "chat_name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "chat_type", nullable = false)
    private ChatType chatType;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.PERSIST)
    private List<UserChat> usersInChat;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.PERSIST)
    private List<ChatMessage> chatMessages;

    public Chat() {
    }

    public Chat(String name, LocalDateTime createdAt, ChatType chatType) {
        this.name = name;
        this.createdAt = createdAt;
        this.chatType = chatType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }

    public List<UserChat> getUsersInChat() {
        return usersInChat;
    }

    public void setUsersInChat(List<UserChat> usersInChat) {
        this.usersInChat = usersInChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id) && Objects.equals(name, chat.name) && Objects.equals(createdAt, chat.createdAt) && chatType == chat.chatType && Objects.equals(usersInChat, chat.usersInChat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, chatType, usersInChat);
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", chatType=" + chatType +
                '}';
    }
}



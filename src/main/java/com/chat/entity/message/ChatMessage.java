package com.chat.entity.message;

import com.chat.entity.chat.Chat;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CHAT_MESSAGE")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAT_MESSAGE_SEQUENCE")
    @SequenceGenerator(name = "CHAT_MESSAGE_SEQUENCE")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "chat_id",
            foreignKey = @ForeignKey(name = "FK_CHAT_MESSAGE_CHAT_ID_CHAT_ID"),
            nullable = false,
            referencedColumnName = "id"
    )
    private Chat chat;

    @OneToOne
    @JoinColumn(
            name = "message_id",
            foreignKey = @ForeignKey(name = "FK_CHAT_MESSAGE_MESSAGE_ID_MESSAGE_ID"),
            nullable = false,
            referencedColumnName = "id"
    )
    private Message message;

    public ChatMessage() {
    }

    public ChatMessage(Chat chat, Message message) {
        this.chat = chat;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return Objects.equals(id, that.id) && Objects.equals(chat, that.chat) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chat, message);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", chat=" + chat +
                ", message=" + message +
                '}';
    }
}

package com.chat.entity.message;

import com.chat.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "MESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_SEQUENCE")
    @SequenceGenerator(name = "MESSAGE_SEQUENCE")
    private Long id;

    @Column(name = "message_body", nullable = false, length = 600)
    private String body;

    @Column(name = "sent_at", nullable = false)
    private LocalDateTime sentAt;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "FK_MESSAGE_USER_ID_USER_ID"),
            nullable = false,
            referencedColumnName = "id"
    )
    private User sentBy;

    @OneToOne(mappedBy = "message", cascade = CascadeType.PERSIST)
    private ChatMessage messageInChat;

    @OneToOne(mappedBy = "message", cascade = CascadeType.PERSIST)
    private UserMessage userMessage;

    public Message() {
    }

    public Message(String body, User sentBy, LocalDateTime sentAt) {
        this.body = body;
        this.sentBy = sentBy;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getSentBy() {
        return sentBy;
    }

    public void setSentBy(User sentBy) {
        this.sentBy = sentBy;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    public ChatMessage getMessageInChat() {
        return messageInChat;
    }

    public void setMessageInChat(ChatMessage messageInChat) {
        this.messageInChat = messageInChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(body, message.body) && Objects.equals(sentAt, message.sentAt) && Objects.equals(messageInChat, message.messageInChat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, sentBy, sentAt, messageInChat);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", sentBy='" + sentBy + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}

package com.chat.entity.message;

import com.chat.entity.message.type.MessageStatusType;
import com.chat.entity.user.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "USER_MESSAGE")
public class UserMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_MESSAGE_SEQUENCE")
    @SequenceGenerator(name = "USER_MESSAGE_SEQUENCE")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "FK_USER_MESSAGE_USER_ID_USER_ID"),
            nullable = false,
            referencedColumnName = "id"
    )
    private User sentTo;

    @OneToOne
    @JoinColumn(
            name = "message_id",
            foreignKey = @ForeignKey(name = "FK_USER_MESSAGE_MESSAGE_ID_MESSAGE_ID"),
            nullable = false,
            referencedColumnName = "id"
    )
    private Message message;

    @Column(name = "message_status_type", nullable = false)
    private MessageStatusType messageStatusType;

    public UserMessage() {
    }

    public UserMessage(User sentTo, Message message, MessageStatusType messageStatusType) {
        this.sentTo = sentTo;
        this.message = message;
        this.messageStatusType = messageStatusType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSentTo() {
        return sentTo;
    }

    public void setUser(User sentTo) {
        this.sentTo = sentTo;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public MessageStatusType getMessageStatusType() {
        return messageStatusType;
    }

    public void setMessageStatusType(MessageStatusType messageStatusType) {
        this.messageStatusType = messageStatusType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMessage that = (UserMessage) o;
        return Objects.equals(id, that.id) && Objects.equals(sentTo, that.sentTo) && Objects.equals(message, that.message) && messageStatusType == that.messageStatusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sentTo, message, messageStatusType);
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "id=" + id +
                ", sentTo=" + sentTo +
                ", message=" + message +
                ", messageStatusType=" + messageStatusType +
                '}';
    }
}

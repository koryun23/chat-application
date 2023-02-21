package com.chat.service.core.message.persist;

import com.chat.entity.message.type.MessageStatusType;

import java.util.Objects;

public class UserMessageCreationParams {

    private String sentTo;
    private Long messageId;
    private MessageStatusType messageStatusType;

    public UserMessageCreationParams() {
    }

    public UserMessageCreationParams(String sentTo, Long messageId, MessageStatusType messageStatusType) {
        this.sentTo = sentTo;
        this.messageId = messageId;
        this.messageStatusType = messageStatusType;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
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
        UserMessageCreationParams that = (UserMessageCreationParams) o;
        return Objects.equals(sentTo, that.sentTo) && Objects.equals(messageId, that.messageId) && messageStatusType == that.messageStatusType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentTo, messageId, messageStatusType);
    }

    @Override
    public String toString() {
        return "UserMessageCreationParams{" +
                "sentTo='" + sentTo + '\'' +
                ", messageId=" + messageId +
                ", messageStatusType=" + messageStatusType +
                '}';
    }
}


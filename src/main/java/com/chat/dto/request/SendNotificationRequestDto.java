package com.chat.dto.request;

import com.chat.dto.type.NotificationType;

import java.util.Objects;

public class SendNotificationRequestDto {

    private NotificationType notificationType;
    private String sentTo;

    public SendNotificationRequestDto() {
    }

    public SendNotificationRequestDto(NotificationType notificationType, String sentTo) {
        this.notificationType = notificationType;
        this.sentTo = sentTo;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getSentTo() {
        return sentTo;
    }

    public void setSentTo(String sentTo) {
        this.sentTo = sentTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendNotificationRequestDto that = (SendNotificationRequestDto) o;
        return notificationType == that.notificationType && sentTo == that.sentTo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationType, sentTo);
    }

    @Override
    public String toString() {
        return "SendNotificationRequestDto{" +
                "notificationType=" + notificationType +
                "sentTo=" + sentTo +
                '}';
    }
}

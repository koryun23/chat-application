package com.chat.dto.response;

import com.chat.dto.type.NotificationType;

import java.time.LocalDateTime;
import java.util.Objects;

public class SendNotificationResponseDto {

    private NotificationType notificationType;
    private String sentTo;
    private LocalDateTime sentAt;

    public SendNotificationResponseDto(NotificationType notificationType, String sentTo, LocalDateTime sentAt) {
        this.notificationType = notificationType;
        this.sentTo = sentTo;
        this.sentAt = sentAt;
    }

    public SendNotificationResponseDto() {
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

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SendNotificationResponseDto that = (SendNotificationResponseDto) o;
        return notificationType == that.notificationType && Objects.equals(sentTo, that.sentTo) && Objects.equals(sentAt, that.sentAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationType, sentTo, sentAt);
    }

    @Override
    public String toString() {
        return "SendNotificationResponseDto{" +
                "notificationType=" + notificationType +
                ", sentTo='" + sentTo + '\'' +
                ", sentAt=" + sentAt +
                '}';
    }
}

package wealthwise.backend.dtos.notification;

import wealthwise.backend.domain.Notification;

public record NotificationResponseDTO(String message) {
    
    public NotificationResponseDTO(Notification notification) {
        this(notification.getMessage());
    }
}

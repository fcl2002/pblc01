package wealthwise.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import wealthwise.backend.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, String> {
}

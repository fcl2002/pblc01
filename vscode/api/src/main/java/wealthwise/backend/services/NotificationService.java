package wealthwise.backend.services;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wealthwise.backend.domain.Notification;
import wealthwise.backend.domain.User;
import wealthwise.backend.dtos.notification.NotificationRequestDTO;
import wealthwise.backend.dtos.notification.NotificationResponseDTO;
import wealthwise.backend.exceptions.IllegalFieldException;
import wealthwise.backend.exceptions.ResourceNotFoundException;
import wealthwise.backend.repositories.NotificationRepository;
import wealthwise.backend.repositories.UserRepository;

@Service
public class NotificationService {
    
    @Autowired
    private NotificationRepository repository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    public Notification getNotificationById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification with id '" + id + "' not found."));
    }

    public NotificationResponseDTO createNotification(NotificationRequestDTO request) {
    
        if (request.userId() == null || userRepository.existsById(request.userId()))
            throw new IllegalFieldException("User 'id'", "is not valid.");   
        
        User user = service.getUserById(request.userId());

        Notification notification = new Notification();
        notification.setMessage(request.message());
        notification.setUser(user);
        
        List<Notification> notifications = user.getNotifications();
        notifications.add(notification);
        user.setNotifications(notifications);

        NotificationResponseDTO notificationDTO = new NotificationResponseDTO(repository.save(notification));

        return notificationDTO;
    }

    public NotificationResponseDTO updateNotification(Map<String, Object> updates, String id) {

        Notification notification = getNotificationById(id);
        
        updates.forEach((key, value) -> {
            switch(key) {
                case "message":                    
                    notification.setMessage((String) value);
                    break;
                default:
                    throw new IllegalFieldException("'" + (String) key + "'", "is not valid for update.");
            }
        });

        NotificationResponseDTO notificationDTO = new NotificationResponseDTO(repository.save(notification));
        return notificationDTO;
    }

    public void deleteNotification(String id) {
        User user = service.getUserById(getNotificationById(id).getUser().getUsername());

        if(Objects.isNull(user))
            throw new ResourceNotFoundException("User not found.");
        

        List<Notification> notifications = user.getNotifications();
        for(Iterator<Notification> it = notifications.iterator(); it.hasNext();) {
            Notification notification = it.next();
            if(notification.getId().equals(id)){
                notification.setUser(null);
                it.remove();
                break;
            }
        }
        repository.deleteById(id);
    }
}

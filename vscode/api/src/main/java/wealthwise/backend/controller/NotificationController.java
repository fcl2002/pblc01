package wealthwise.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import wealthwise.backend.domain.Notification;
import wealthwise.backend.dtos.notification.NotificationRequestDTO;
import wealthwise.backend.dtos.notification.NotificationResponseDTO;
import wealthwise.backend.repositories.NotificationRepository;
import wealthwise.backend.services.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    
    @Autowired
    private NotificationRepository repository;

    @Autowired
    private NotificationService service;

    @GetMapping
    public ResponseEntity<List<NotificationResponseDTO>> getAll() {
        
        List<NotificationResponseDTO> notifications = this.repository.findAll().stream().map(NotificationResponseDTO::new).toList();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> getNotification(@PathVariable String id) {
        
        Notification notification = service.getNotificationById(id);
        NotificationResponseDTO notificationDTO = new NotificationResponseDTO(notification);
        return ResponseEntity.ok(notificationDTO);
    }

    @PostMapping
    public ResponseEntity<NotificationResponseDTO> postNotificacao(@RequestBody @Valid NotificationRequestDTO request) {
        
        NotificationResponseDTO notificacaoDTO = service.createNotification(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<NotificationResponseDTO> patchNotificacao(@RequestBody Map<String, Object> updates, @PathVariable String id) {
        
        NotificationResponseDTO notificationDTO = service.updateNotification(updates, id);
        return ResponseEntity.status(HttpStatus.OK).body(notificationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotificacao(@PathVariable String id) {
        service.deleteNotification(id);
        return ResponseEntity.ok("Notificacao deleted with id '" + id + "'");
    }
}

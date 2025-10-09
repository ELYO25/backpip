package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.Notification;
import Gestion.Employeer.services.NotificationService;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired private NotificationService notificationService;

    @GetMapping
    public List<Notification> getAll() { return notificationService.getAllNotifications(); }

    @GetMapping("/{id}")
    public Notification getById(@PathVariable Long id) { return notificationService.getNotificationById(id); }

    @GetMapping("/non-lues/{userId}")
    public List<Notification> getNonLues(@PathVariable Long userId) { return notificationService.getNotificationsNonLues(userId); }

    @PostMapping
    public Notification create(@RequestBody Notification n) { return notificationService.ajouterNotification(n); }

    @PutMapping("/{id}/lue")
    public Notification marquerCommeLue(@PathVariable Long id) { return notificationService.marquerCommeLue(id); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.supprimerNotification(id);
        return ResponseEntity.noContent().build();
    }
}

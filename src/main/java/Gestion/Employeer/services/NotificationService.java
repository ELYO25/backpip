package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.Notification;
import Gestion.Employeer.repositories.NotificationRepository;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Notification non trouvée avec id " + id));
    }

    public List<Notification> getNotificationsNonLues(Long userId) {
        return notificationRepository.findByDestinataireIdAndLueFalse(userId);
    }

    public Notification ajouterNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Notification marquerCommeLue(Long id) {
        Notification n = getNotificationById(id);
        n.setLue(true);
        return notificationRepository.save(n);
    }

    public void supprimerNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}

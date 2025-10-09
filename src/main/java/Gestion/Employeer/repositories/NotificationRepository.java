package Gestion.Employeer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Gestion.Employeer.model.Notification;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByDestinataireIdAndLueFalse(Long userId);
}

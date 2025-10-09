package Gestion.Employeer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Gestion.Employeer.model.SuiviTrimestriel;
import java.util.List;

public interface SuiviTrimestrielRepository extends JpaRepository<SuiviTrimestriel, Long> {
    List<SuiviTrimestriel> findByProjetId(Long projetId);
}

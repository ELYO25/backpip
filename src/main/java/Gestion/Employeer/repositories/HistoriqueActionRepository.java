package Gestion.Employeer.repositories;

import Gestion.Employeer.model.HistoriqueAction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoriqueActionRepository extends JpaRepository<HistoriqueAction, Long> {

    // ✅ Correct → accès via la relation
    List<HistoriqueAction> findByUtilisateur_Id(Long utilisateurId);
}

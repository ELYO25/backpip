package Gestion.Employeer.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import Gestion.Employeer.model.Commentaire;
import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {
    List<Commentaire> findByProjetId(Long projetId);
}


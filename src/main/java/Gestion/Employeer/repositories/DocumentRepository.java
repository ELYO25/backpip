package Gestion.Employeer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Gestion.Employeer.model.Document;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByProjetId(Long projetId);
}


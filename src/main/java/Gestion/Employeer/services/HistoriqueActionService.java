package Gestion.Employeer.services;

import Gestion.Employeer.model.HistoriqueAction;
import Gestion.Employeer.model.Utilisateur;
import Gestion.Employeer.repositories.HistoriqueActionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoriqueActionService {

    private final HistoriqueActionRepository historiqueActionRepository;

    // 🔹 Logger une action
    public void logAction(Utilisateur utilisateur, String action, String description) {
        HistoriqueAction historique = HistoriqueAction.builder()
                .utilisateur(utilisateur)
                .action(action)
                .description(description)
                .dateAction(LocalDateTime.now())
                .build();

        historiqueActionRepository.save(historique);
    }

    // 🔹 Lister tout
    public List<HistoriqueAction> getAll() {
        return historiqueActionRepository.findAll();
    }

    // 🔹 Lister par utilisateur
    public List<HistoriqueAction> getByUtilisateur(Long utilisateurId) {
        return historiqueActionRepository.findByUtilisateur_Id(utilisateurId);
    }
}

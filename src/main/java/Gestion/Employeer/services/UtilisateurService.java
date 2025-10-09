package Gestion.Employeer.services;

import Gestion.Employeer.model.Role;
import Gestion.Employeer.model.Utilisateur;
import Gestion.Employeer.repositories.RoleRepository;
import Gestion.Employeer.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UtilisateurService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final HistoriqueActionService historiqueActionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return utilisateurRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + username));
    }

    public Utilisateur create(Utilisateur user) {
        Utilisateur saved = utilisateurRepository.save(user);
        historiqueActionService.logAction(saved, "CREATE_USER", "Utilisateur créé");
        return saved;
    }

    public Utilisateur update(Long id, Utilisateur user) {
        Utilisateur existing = utilisateurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        existing.setNomComplet(user.getNomComplet());
        existing.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().isBlank()) {
            existing.setPassword(user.getPassword()); // si tu encodes ailleurs, adapte
        }
        if (user.getRole() != null) {
            existing.setRole(user.getRole());
        }
        existing.setMinistere(user.getMinistere());
        existing.setRegion(user.getRegion());
        existing.setDistrict(user.getDistrict());

        Utilisateur updated = utilisateurRepository.save(existing);
        historiqueActionService.logAction(updated, "UPDATE_USER", "Utilisateur mis à jour");
        return updated;
    }

    public void delete(Long id) {
        Utilisateur user = utilisateurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        utilisateurRepository.deleteById(id);
        historiqueActionService.logAction(user, "DELETE_USER", "Utilisateur supprimé");
    }

    public List<Utilisateur> getAll() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getById(Long id) {
        return utilisateurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

    /** 🔹 Changement de rôle par ID */
    public Utilisateur changeRole(Long id, Long roleId) {
        Utilisateur user = utilisateurRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
        Role role = roleRepository.findById(roleId)
            .orElseThrow(() -> new RuntimeException("Rôle introuvable: " + roleId));
        user.setRole(role);
        Utilisateur updated = utilisateurRepository.save(user);
        historiqueActionService.logAction(updated, "CHANGE_ROLE", "Rôle changé (niveau " + role.getNiveau() + ")");
        return updated;
    }
}

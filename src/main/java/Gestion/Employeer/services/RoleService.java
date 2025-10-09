package Gestion.Employeer.services;

import Gestion.Employeer.model.Role;
import Gestion.Employeer.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final HistoriqueActionService historiqueActionService;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role getById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rôle introuvable"));
    }

    public Role create(Role role) {
        Role saved = roleRepository.save(role);
        historiqueActionService.logAction(null, "CREATE_ROLE", "Rôle créé : " + role.getNom());
        return saved;
    }

    public Role update(Long id, Role role) {
        Role existing = getById(id);
        existing.setNom(role.getNom());
        existing.setNiveau(role.getNiveau());
        Role updated = roleRepository.save(existing);
        historiqueActionService.logAction(null, "UPDATE_ROLE", "Rôle mis à jour : " + role.getNom());
        return updated;
    }

    public void delete(Long id) {
        Role role = getById(id);
        roleRepository.deleteById(id);
        historiqueActionService.logAction(null, "DELETE_ROLE", "Rôle supprimé : " + role.getNom());
    }
}

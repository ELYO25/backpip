package Gestion.Employeer.repositories;

import Gestion.Employeer.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByNom(String nom);
    Optional<Role> findByNiveau(int niveau);
    Optional<Role> findFirstByNiveau(int niveau);
}

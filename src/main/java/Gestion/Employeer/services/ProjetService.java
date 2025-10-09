package Gestion.Employeer.services;

import Gestion.Employeer.model.*;
import Gestion.Employeer.repositories.ProjetRepository;
import Gestion.Employeer.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProjetService {

    private final ProjetRepository projetRepository;
    private final UtilisateurRepository utilisateurRepository;

    public List<Projet> getProjetsPourUtilisateur(Utilisateur user) {
        int niveau = user.getRole() != null ? user.getRole().getNiveau() : 0;

        // Niv. 5 : Admin => tout
        if (niveau >= 5) {
            return projetRepository.findAll();
        }

        // Niv. 4 : Ministère
        if (niveau == 4) {
            Ministere scope = (user.getRole().getMinistere() != null)
                    ? user.getRole().getMinistere()
                    : user.getMinistere();
            if (scope == null) return List.of();
            return projetRepository.findByMinistere(scope);
        }

        // Niv. 3 : Région (profil + missions)
        if (niveau == 3) {
            Set<Region> regions = new HashSet<>();
            if (user.getRole().getRegion() != null) regions.add(user.getRole().getRegion());
            if (user.getRegion() != null) regions.add(user.getRegion());
            regions.addAll(user.getRegionsAssignes());
            if (regions.isEmpty()) return List.of();
            return projetRepository.findByRegionIn(regions);
        }

        // Niv. 2 : District (profil + missions)
        if (niveau == 2) {
            Set<District> districts = new HashSet<>();
            if (user.getRole().getDistrict() != null) districts.add(user.getRole().getDistrict());
            if (user.getDistrict() != null) districts.add(user.getDistrict());
            districts.addAll(user.getDistrictsAssignes());
            if (districts.isEmpty()) return List.of();
            return projetRepository.findByDistrictIn(districts);
        }

        // Niv. 1 : Agent simple = projets explicitement assignés à l’utilisateur
        // (requiert la relation ManyToMany Projet<->Utilisateur côté entity)
        return projetRepository.findByUtilisateursContains(user);
    }

    public Projet getProjetPourUtilisateur(Utilisateur user, Long projetId) {
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new RuntimeException("Projet introuvable"));

        List<Projet> accessibles = getProjetsPourUtilisateur(user);
        if (!accessibles.contains(projet)) {
            throw new AccessDeniedException("Accès refusé au projet " + projetId);
        }
        return projet;
    }

    public Projet createProjet(Projet projet, Utilisateur user) {
        int niveau = user.getRole() != null ? user.getRole().getNiveau() : 0;
        if (niveau < 4) {
            throw new AccessDeniedException("Seul Admin (5) ou Ministère (4) peuvent créer un projet");
        }
        return projetRepository.save(projet);
    }

    public Projet updateProjet(Long id, Projet updated, Utilisateur user) {
        Projet existing = getProjetPourUtilisateur(user, id);
        existing.setDesignation(updated.getDesignation());
        existing.setStatut(updated.getStatut());
        existing.setExercice(updated.getExercice());
        existing.setTauxPhysique(updated.getTauxPhysique());
        existing.setDuree(updated.getDuree());
        existing.setObservation(updated.getObservation());
        existing.setMinistere(updated.getMinistere());
        existing.setRegion(updated.getRegion());
        existing.setDistrict(updated.getDistrict());
        existing.setCommune(updated.getCommune());
        return projetRepository.save(existing);
    }

    public void deleteProjet(Long id, Utilisateur user) {
        int niveau = user.getRole() != null ? user.getRole().getNiveau() : 0;
        if (niveau < 5) {
            throw new AccessDeniedException("Seul Admin (5) peut supprimer un projet");
        }
        Projet existing = getProjetPourUtilisateur(user, id);
        projetRepository.delete(existing);
    }
}

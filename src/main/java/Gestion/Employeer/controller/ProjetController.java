package Gestion.Employeer.controller;

import Gestion.Employeer.model.Projet;
import Gestion.Employeer.model.Utilisateur;
import Gestion.Employeer.services.ProjetService;
import Gestion.Employeer.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projets")
@RequiredArgsConstructor
public class ProjetController {

    private final ProjetService projetService;
    private final UtilisateurService utilisateurService;

    // 🔹 Récupérer tous les projets accessibles par l’utilisateur connecté
    @GetMapping
    public List<Projet> getAll(@AuthenticationPrincipal Utilisateur user) {
        return projetService.getProjetsPourUtilisateur(user);
    }

    // 🔹 Récupérer un projet par ID (si autorisé)
    @GetMapping("/{id}")
    public Projet getById(@PathVariable Long id, @AuthenticationPrincipal Utilisateur user) {
        return projetService.getProjetPourUtilisateur(user, id);
    }

    // 🔹 Créer un projet (ADMIN ou MINISTERE)
    @PostMapping
    public Projet create(@RequestBody Projet projet, @AuthenticationPrincipal Utilisateur user) {
        return projetService.createProjet(projet, user);
    }

    // 🔹 Modifier un projet
    @PutMapping("/{id}")
    public Projet update(@PathVariable Long id, @RequestBody Projet projet, @AuthenticationPrincipal Utilisateur user) {
        return projetService.updateProjet(id, projet, user);
    }

    // 🔹 Supprimer un projet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @AuthenticationPrincipal Utilisateur user) {
        projetService.deleteProjet(id, user);
        return ResponseEntity.noContent().build();
    }
}

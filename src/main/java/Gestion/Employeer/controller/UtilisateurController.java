package Gestion.Employeer.controller;

import Gestion.Employeer.model.Utilisateur;
import Gestion.Employeer.services.UtilisateurService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAll() {
        return ResponseEntity.ok(utilisateurService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getById(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Utilisateur> create(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.create(utilisateur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.update(id, utilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        utilisateurService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** 🔹 Nouveau: changer le rôle via roleId */
    @PutMapping("/{id}/role")
    public ResponseEntity<Utilisateur> changeRole(@PathVariable Long id, @RequestParam Long roleId) {
        return ResponseEntity.ok(utilisateurService.changeRole(id, roleId));
    }
}

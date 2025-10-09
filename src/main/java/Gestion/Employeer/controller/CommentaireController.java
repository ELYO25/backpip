package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.Commentaire;
import Gestion.Employeer.services.CommentaireService;

import java.util.List;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    @Autowired private CommentaireService commentaireService;

    @GetMapping
    public List<Commentaire> getAll() { return commentaireService.getAllCommentaires(); }

    @GetMapping("/{id}")
    public Commentaire getById(@PathVariable Long id) { return commentaireService.getCommentaireById(id); }

    @GetMapping("/projet/{projetId}")
    public List<Commentaire> getByProjet(@PathVariable Long projetId) { return commentaireService.getCommentairesByProjet(projetId); }

    @PostMapping
    public Commentaire create(@RequestBody Commentaire c) { return commentaireService.ajouterCommentaire(c); }

    @PutMapping("/{id}")
    public Commentaire update(@PathVariable Long id, @RequestBody Commentaire c) { return commentaireService.modifierCommentaire(id, c); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        commentaireService.supprimerCommentaire(id);
        return ResponseEntity.noContent().build();
    }
}

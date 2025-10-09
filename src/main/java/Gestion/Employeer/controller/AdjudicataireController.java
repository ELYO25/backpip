package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.Adjudicataire;
import Gestion.Employeer.services.AdjudicataireService;

import java.util.List;

@RestController
@RequestMapping("/api/adjudicataires")
public class AdjudicataireController {

    @Autowired private AdjudicataireService adjudicataireService;

    @GetMapping
    public List<Adjudicataire> getAll() { return adjudicataireService.getAllAdjudicataires(); }

    @GetMapping("/{id}")
    public Adjudicataire getById(@PathVariable Long id) { return adjudicataireService.getAdjudicataireById(id); }

    @PostMapping
    public Adjudicataire create(@RequestBody Adjudicataire a) { return adjudicataireService.ajouterAdjudicataire(a); }

    @PutMapping("/{id}")
    public Adjudicataire update(@PathVariable Long id, @RequestBody Adjudicataire a) { return adjudicataireService.modifierAdjudicataire(id, a); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        adjudicataireService.supprimerAdjudicataire(id);
        return ResponseEntity.noContent().build();
    }
}

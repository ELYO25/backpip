package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.SuiviTrimestriel;
import Gestion.Employeer.services.SuiviTrimestrielService;

import java.util.List;

@RestController
@RequestMapping("/api/suivis")
public class SuiviTrimestrielController {

    @Autowired private SuiviTrimestrielService suiviService;

    @GetMapping
    public List<SuiviTrimestriel> getAll() { return suiviService.getAllSuivis(); }

    @GetMapping("/{id}")
    public SuiviTrimestriel getById(@PathVariable Long id) { return suiviService.getSuiviById(id); }

    @GetMapping("/projet/{projetId}")
    public List<SuiviTrimestriel> getByProjet(@PathVariable Long projetId) { return suiviService.getSuivisByProjet(projetId); }

    @PostMapping
    public SuiviTrimestriel create(@RequestBody SuiviTrimestriel s) { return suiviService.ajouterSuivi(s); }

    @PutMapping("/{id}")
    public SuiviTrimestriel update(@PathVariable Long id, @RequestBody SuiviTrimestriel s) { return suiviService.modifierSuivi(id, s); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        suiviService.supprimerSuivi(id);
        return ResponseEntity.noContent().build();
    }
}

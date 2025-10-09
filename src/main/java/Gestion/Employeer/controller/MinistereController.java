package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.Ministere;
import Gestion.Employeer.services.MinistereService;

import java.util.List;

@RestController
@RequestMapping("/api/ministeres")
public class MinistereController {

    @Autowired private MinistereService ministereService;

    @GetMapping
    public List<Ministere> getAll() { return ministereService.getAllMinisteres(); }

    @GetMapping("/{id}")
    public Ministere getById(@PathVariable Long id) { return ministereService.getMinistereById(id); }

    @PostMapping
    public Ministere create(@RequestBody Ministere m) { return ministereService.ajouterMinistere(m); }

    @PutMapping("/{id}")
    public Ministere update(@PathVariable Long id, @RequestBody Ministere m) { return ministereService.modifierMinistere(id, m); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ministereService.supprimerMinistere(id);
        return ResponseEntity.noContent().build();
    }
}

package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.Commune;
import Gestion.Employeer.services.CommuneService;

import java.util.List;

@RestController
@RequestMapping("/api/communes")
public class CommuneController {

    @Autowired private CommuneService communeService;

    @GetMapping
    public List<Commune> getAll() { return communeService.getAllCommunes(); }

    @GetMapping("/{id}")
    public Commune getById(@PathVariable Long id) { return communeService.getCommuneById(id); }

    @GetMapping("/district/{districtId}")
    public List<Commune> getByDistrict(@PathVariable Long districtId) { return communeService.getCommunesByDistrict(districtId); }

    @PostMapping
    public Commune create(@RequestBody Commune c) { return communeService.ajouterCommune(c); }

    @PutMapping("/{id}")
    public Commune update(@PathVariable Long id, @RequestBody Commune c) { return communeService.modifierCommune(id, c); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        communeService.supprimerCommune(id);
        return ResponseEntity.noContent().build();
    }
}

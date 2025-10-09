package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.Region;
import Gestion.Employeer.services.RegionService;

import java.util.List;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired private RegionService regionService;

    @GetMapping
    public List<Region> getAll() { return regionService.getAllRegions(); }

    @GetMapping("/{id}")
    public Region getById(@PathVariable Long id) { return regionService.getRegionById(id); }

    @PostMapping
    public Region create(@RequestBody Region r) { return regionService.ajouterRegion(r); }

    @PutMapping("/{id}")
    public Region update(@PathVariable Long id, @RequestBody Region r) { return regionService.modifierRegion(id, r); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        regionService.supprimerRegion(id);
        return ResponseEntity.noContent().build();
    }
}
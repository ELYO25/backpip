package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.District;
import Gestion.Employeer.services.DistrictService;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictController {

    @Autowired private DistrictService districtService;

    @GetMapping
    public List<District> getAll() { return districtService.getAllDistricts(); }

    @GetMapping("/{id}")
    public District getById(@PathVariable Long id) { return districtService.getDistrictById(id); }

    @GetMapping("/region/{regionId}")
    public List<District> getByRegion(@PathVariable Long regionId) { return districtService.getDistrictsByRegion(regionId); }

    @PostMapping
    public District create(@RequestBody District d) { return districtService.ajouterDistrict(d); }

    @PutMapping("/{id}")
    public District update(@PathVariable Long id, @RequestBody District d) { return districtService.modifierDistrict(id, d); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        districtService.supprimerDistrict(id);
        return ResponseEntity.noContent().build();
    }
}

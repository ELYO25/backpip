package Gestion.Employeer.controller;

import Gestion.Employeer.model.SuiviBudgetaire;
import Gestion.Employeer.services.SuiviBudgetaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suivi-budgetaire")
public class SuiviBudgetaireController {

    @Autowired
    private SuiviBudgetaireService service;

    @GetMapping
    public List<SuiviBudgetaire> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SuiviBudgetaire getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public SuiviBudgetaire create(@RequestBody SuiviBudgetaire suivi) {
        return service.create(suivi);
    }

    @PutMapping("/{id}")
    public SuiviBudgetaire update(@PathVariable Long id, @RequestBody SuiviBudgetaire suivi) {
        return service.update(id, suivi);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

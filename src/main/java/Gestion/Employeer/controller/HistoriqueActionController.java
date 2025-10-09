package Gestion.Employeer.controller;

import Gestion.Employeer.model.HistoriqueAction;
import Gestion.Employeer.services.HistoriqueActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historiques")
@RequiredArgsConstructor
public class HistoriqueActionController {

    private final HistoriqueActionService historiqueActionService;

    // 🔹 Lister tout
    @GetMapping
    public ResponseEntity<List<HistoriqueAction>> getAll() {
        return ResponseEntity.ok(historiqueActionService.getAll());
    }

    // 🔹 Lister par utilisateur
    @GetMapping("/utilisateur/{id}")
    public ResponseEntity<List<HistoriqueAction>> getByUtilisateur(@PathVariable Long id) {
        return ResponseEntity.ok(historiqueActionService.getByUtilisateur(id));
    }
}

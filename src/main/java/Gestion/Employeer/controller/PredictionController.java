package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.services.PredictionService;

@RestController
@RequestMapping("/api/predictions")
public class PredictionController {

    @Autowired private PredictionService predictionService;

    @GetMapping("/projet/{projetId}")
    public String predict(@PathVariable Long projetId) {
        return predictionService.predictProjet(projetId);
    }
}

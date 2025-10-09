package Gestion.Employeer.services;

import org.springframework.stereotype.Service;

@Service
public class PredictionService {

    // Pour l’instant c’est un placeholder.
    // Tu pourras plus tard intégrer TensorFlow, Scikit-learn ou un modèle custom.

    public String predictProjet(Long projetId) {
        // Simulation simple
        return "Projet " + projetId + " : risque de retard faible, achèvement estimé dans 90 jours.";
    }
}

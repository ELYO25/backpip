package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.Adjudicataire;
import Gestion.Employeer.repositories.AdjudicataireRepository;

import java.util.List;

@Service
public class AdjudicataireService {

    @Autowired
    private AdjudicataireRepository adjudicataireRepository;

    public List<Adjudicataire> getAllAdjudicataires() {
        return adjudicataireRepository.findAll();
    }

    public Adjudicataire getAdjudicataireById(Long id) {
        return adjudicataireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Adjudicataire non trouvé avec id " + id));
    }

    public Adjudicataire ajouterAdjudicataire(Adjudicataire adjudicataire) {
        return adjudicataireRepository.save(adjudicataire);
    }

    public Adjudicataire modifierAdjudicataire(Long id, Adjudicataire details) {
        Adjudicataire a = getAdjudicataireById(id);
        a.setNom(details.getNom());
        return adjudicataireRepository.save(a);
    }

    public void supprimerAdjudicataire(Long id) {
        adjudicataireRepository.deleteById(id);
    }
}

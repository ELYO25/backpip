package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.Ministere;
import Gestion.Employeer.repositories.MinistereRepository;

import java.util.List;

@Service
public class MinistereService {

    @Autowired
    private MinistereRepository ministereRepository;

    public List<Ministere> getAllMinisteres() {
        return ministereRepository.findAll();
    }

    public Ministere getMinistereById(Long id) {
        return ministereRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ministère non trouvé avec id " + id));
    }

    public Ministere ajouterMinistere(Ministere ministere) {
        return ministereRepository.save(ministere);
    }

    public Ministere modifierMinistere(Long id, Ministere details) {
        Ministere m = getMinistereById(id);
        m.setNom(details.getNom());
        return ministereRepository.save(m);
    }

    public void supprimerMinistere(Long id) {
        ministereRepository.deleteById(id);
    }
}

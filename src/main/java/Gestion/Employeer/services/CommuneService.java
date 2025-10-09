package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.Commune;
import Gestion.Employeer.repositories.CommuneRepository;

import java.util.List;

@Service
public class CommuneService {

    @Autowired
    private CommuneRepository communeRepository;

    public List<Commune> getAllCommunes() {
        return communeRepository.findAll();
    }

    public Commune getCommuneById(Long id) {
        return communeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commune non trouvée avec id " + id));
    }

    public List<Commune> getCommunesByDistrict(Long districtId) {
        return communeRepository.findByDistrictId(districtId);
    }

    public Commune ajouterCommune(Commune commune) {
        return communeRepository.save(commune);
    }

    public Commune modifierCommune(Long id, Commune details) {
        Commune c = getCommuneById(id);
        c.setNom(details.getNom());
        c.setDistrict(details.getDistrict());
        return communeRepository.save(c);
    }

    public void supprimerCommune(Long id) {
        communeRepository.deleteById(id);
    }
}

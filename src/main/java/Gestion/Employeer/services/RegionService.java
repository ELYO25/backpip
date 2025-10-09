package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.Region;
import Gestion.Employeer.repositories.RegionRepository;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Region getRegionById(Long id) {
        return regionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Région non trouvée avec id " + id));
    }

    public Region ajouterRegion(Region region) {
        return regionRepository.save(region);
    }

    public Region modifierRegion(Long id, Region details) {
        Region r = getRegionById(id);
        r.setNom(details.getNom());
        return regionRepository.save(r);
    }

    public void supprimerRegion(Long id) {
        regionRepository.deleteById(id);
    }
}

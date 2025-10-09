package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.District;
import Gestion.Employeer.repositories.DistrictRepository;

import java.util.List;

@Service
public class DistrictService {

    @Autowired
    private DistrictRepository districtRepository;

    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    public District getDistrictById(Long id) {
        return districtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("District non trouvé avec id " + id));
    }

    public List<District> getDistrictsByRegion(Long regionId) {
        return districtRepository.findByRegionId(regionId);
    }

    public District ajouterDistrict(District district) {
        return districtRepository.save(district);
    }

    public District modifierDistrict(Long id, District details) {
        District d = getDistrictById(id);
        d.setNom(details.getNom());
        d.setRegion(details.getRegion());
        return districtRepository.save(d);
    }

    public void supprimerDistrict(Long id) {
        districtRepository.deleteById(id);
    }
}

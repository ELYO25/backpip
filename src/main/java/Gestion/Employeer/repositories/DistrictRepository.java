package Gestion.Employeer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Gestion.Employeer.model.District;
import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Long> {
    List<District> findByRegionId(Long regionId);
}

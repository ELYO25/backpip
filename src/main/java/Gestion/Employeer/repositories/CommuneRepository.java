package Gestion.Employeer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Gestion.Employeer.model.Commune;
import java.util.List;

public interface CommuneRepository extends JpaRepository<Commune, Long> {
    List<Commune> findByDistrictId(Long districtId);
}


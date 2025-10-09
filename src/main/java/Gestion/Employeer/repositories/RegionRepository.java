package Gestion.Employeer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import Gestion.Employeer.model.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}


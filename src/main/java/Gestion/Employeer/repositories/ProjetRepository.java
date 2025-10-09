package Gestion.Employeer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import Gestion.Employeer.model.District;
import Gestion.Employeer.model.Ministere;
import Gestion.Employeer.model.Projet;
import Gestion.Employeer.model.Region;
import Gestion.Employeer.model.Utilisateur;

import java.util.List;
import java.util.Set;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
	List<Projet> findByMinistere(Ministere ministere);
	List<Projet> findByRegionIn(Set<Region> regions);
	List<Projet> findByDistrictIn(Set<District> districts);
	List<Projet> findByUtilisateursContains(Utilisateur utilisateur);
	
}


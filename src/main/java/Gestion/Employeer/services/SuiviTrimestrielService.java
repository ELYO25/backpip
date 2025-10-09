package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.SuiviTrimestriel;
import Gestion.Employeer.repositories.SuiviTrimestrielRepository;

import java.util.List;

@Service
public class SuiviTrimestrielService {

    @Autowired
    private SuiviTrimestrielRepository suiviRepository;

    public List<SuiviTrimestriel> getAllSuivis() {
        return suiviRepository.findAll();
    }

    public SuiviTrimestriel getSuiviById(Long id) {
        return suiviRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suivi trimestriel non trouvé avec id " + id));
    }

    public List<SuiviTrimestriel> getSuivisByProjet(Long projetId) {
        return suiviRepository.findByProjetId(projetId);
    }

    public SuiviTrimestriel ajouterSuivi(SuiviTrimestriel suivi) {
        return suiviRepository.save(suivi);
    }

    public SuiviTrimestriel modifierSuivi(Long id, SuiviTrimestriel details) {
        SuiviTrimestriel s = getSuiviById(id);
        s.setTrimestre(details.getTrimestre());
        s.setTauxRealisation(details.getTauxRealisation());
        s.setMontantConsomme(details.getMontantConsomme());
        s.setCommentaire(details.getCommentaire());
        s.setProjet(details.getProjet());
        return suiviRepository.save(s);
    }

    public void supprimerSuivi(Long id) {
        suiviRepository.deleteById(id);
    }
}
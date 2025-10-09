package Gestion.Employeer.services;

import Gestion.Employeer.model.SuiviBudgetaire;
import Gestion.Employeer.repositories.SuiviBudgetaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuiviBudgetaireService {

    @Autowired
    private SuiviBudgetaireRepository repo;

    public List<SuiviBudgetaire> getAll() {
        return repo.findAll();
    }

    public SuiviBudgetaire getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Suivi budgetaire introuvable"));
    }

    public SuiviBudgetaire create(SuiviBudgetaire suivi) {
        return repo.save(suivi);
    }

    public SuiviBudgetaire update(Long id, SuiviBudgetaire suivi) {
        SuiviBudgetaire existant = getById(id);
        existant.setCreditOuvertAE(suivi.getCreditOuvertAE());
        existant.setCreditConsommeAE(suivi.getCreditConsommeAE());
        existant.setCreditOuvertCP(suivi.getCreditOuvertCP());
        existant.setCreditConsommeCP(suivi.getCreditConsommeCP());
        existant.setProjet(suivi.getProjet());
        existant.setDateSuivi(suivi.getDateSuivi());
        return repo.save(existant);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}

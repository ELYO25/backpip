package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.Commentaire;
import Gestion.Employeer.repositories.CommentaireRepository;

import java.util.List;

@Service
public class CommentaireService {

    @Autowired
    private CommentaireRepository commentaireRepository;

    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    public Commentaire getCommentaireById(Long id) {
        return commentaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commentaire non trouvé avec id " + id));
    }

    public List<Commentaire> getCommentairesByProjet(Long projetId) {
        return commentaireRepository.findByProjetId(projetId);
    }

    public Commentaire ajouterCommentaire(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    public Commentaire modifierCommentaire(Long id, Commentaire details) {
        Commentaire c = getCommentaireById(id);
        c.setContenu(details.getContenu());
        c.setPhotoUrl(details.getPhotoUrl());
        c.setDateCommentaire(details.getDateCommentaire());
        c.setAuteur(details.getAuteur());
        c.setProjet(details.getProjet());
        return commentaireRepository.save(c);
    }

    public void supprimerCommentaire(Long id) {
        commentaireRepository.deleteById(id);
    }
}

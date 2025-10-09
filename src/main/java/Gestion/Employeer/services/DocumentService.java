package Gestion.Employeer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Gestion.Employeer.exception.ResourceNotFoundException;
import Gestion.Employeer.model.Document;
import Gestion.Employeer.repositories.DocumentRepository;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document non trouvé avec id " + id));
    }

    public List<Document> getDocumentsByProjet(Long projetId) {
        return documentRepository.findByProjetId(projetId);
    }

    public Document ajouterDocument(Document document) {
        return documentRepository.save(document);
    }

    public Document modifierDocument(Long id, Document details) {
        Document d = getDocumentById(id);
        d.setNom(details.getNom());
        d.setType(details.getType());
        d.setUrl(details.getUrl());
        d.setProjet(details.getProjet());
        return documentRepository.save(d);
    }

    public void supprimerDocument(Long id) {
        documentRepository.deleteById(id);
    }
}

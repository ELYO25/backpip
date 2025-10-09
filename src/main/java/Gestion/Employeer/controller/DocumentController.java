package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.model.Document;
import Gestion.Employeer.services.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired private DocumentService documentService;

    @GetMapping
    public List<Document> getAll() { return documentService.getAllDocuments(); }

    @GetMapping("/{id}")
    public Document getById(@PathVariable Long id) { return documentService.getDocumentById(id); }

    @GetMapping("/projet/{projetId}")
    public List<Document> getByProjet(@PathVariable Long projetId) { return documentService.getDocumentsByProjet(projetId); }

    @PostMapping
    public Document create(@RequestBody Document d) { return documentService.ajouterDocument(d); }

    @PutMapping("/{id}")
    public Document update(@PathVariable Long id, @RequestBody Document d) { return documentService.modifierDocument(id, d); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        documentService.supprimerDocument(id);
        return ResponseEntity.noContent().build();
    }
}

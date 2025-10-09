package Gestion.Employeer.services;

import org.springframework.stereotype.Service;

@Service
public class ExportService {

    // Pour l’instant, on renvoie juste un byte[] vide.
    // Tu pourras utiliser iText ou Apache POI pour générer PDF/Excel.

    public byte[] exportProjetToPdf(Long projetId) {
        // TODO: Implémenter la génération PDF
        return new byte[0];
    }

    public byte[] exportProjetToExcel(Long projetId) {
        // TODO: Implémenter la génération Excel
        return new byte[0];
    }
}

package Gestion.Employeer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import Gestion.Employeer.services.ExportService;

@RestController
@RequestMapping("/api/exports")
public class ExportController {

    @Autowired private ExportService exportService;

    @GetMapping("/projet/{projetId}/pdf")
    public ResponseEntity<byte[]> exportPdf(@PathVariable Long projetId) {
        byte[] data = exportService.exportProjetToPdf(projetId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=projet_"+projetId+".pdf")
                .body(data);
    }

    @GetMapping("/projet/{projetId}/excel")
    public ResponseEntity<byte[]> exportExcel(@PathVariable Long projetId) {
        byte[] data = exportService.exportProjetToExcel(projetId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=projet_"+projetId+".xlsx")
                .body(data);
    }
}

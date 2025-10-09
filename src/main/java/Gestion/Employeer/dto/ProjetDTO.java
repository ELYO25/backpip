package Gestion.Employeer.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ProjetDTO {
    private Long id;
    private String designation;
    private String statut;
    private String exercice;

    private Long ministereId;
    private Long regionId;
    private Long districtId;
    private Long communeId;
    private Long adjudicataireId;

    private LocalDate dateOrdreService;
    private Integer duree;
    private Double tauxPhysique;
    private String observation;
    private Double latitude;
    private Double longitude;
}

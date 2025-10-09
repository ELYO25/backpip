package Gestion.Employeer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "suivi_trimestriel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuiviTrimestriel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trimestre; // T1, T2, T3, T4

    private double tauxRealisation;

    private double montantConsomme;

    private String commentaire;

    @ManyToOne
    private Projet projet;
}

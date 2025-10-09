package Gestion.Employeer.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "suiviBudgetaire")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuiviBudgetaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double creditOuvertAE;
    private Double creditConsommeAE;
    private Double creditOuvertCP;
    private Double creditConsommeCP;

    @ManyToOne
    private Projet projet;

    private LocalDate dateSuivi;

    // Getters & Setters
}

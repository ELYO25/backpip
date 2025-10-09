package Gestion.Employeer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prediction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estimationDateFin;
    private String risqueDepassement;

    @OneToOne
    private Projet projet;

    // Getters & Setters
}

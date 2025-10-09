package Gestion.Employeer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ministeres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ministere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
}


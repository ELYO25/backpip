package Gestion.Employeer.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nom personnalisé du rôle (ex: "Ministère Finances")
    @Column(nullable = false, unique = true)
    private String nom;

    // Niveau d'accès (1 = agent simple → 5 = admin)
    @Column(nullable = false)
    private int niveau;
    
    // Relations possibles avec des entités métier
    @ManyToOne private Ministere ministere;
    @ManyToOne private Region region;
    @ManyToOne private District district;
}

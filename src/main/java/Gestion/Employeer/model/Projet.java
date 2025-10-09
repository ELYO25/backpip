package Gestion.Employeer.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String designation;

    private String statut; // en cours, achevé, abandonné...

    private String exercice;

    @ManyToOne
    private Ministere ministere;

    @ManyToOne
    private Region region;

    @ManyToOne
    private District district;

    @ManyToOne
    private Commune commune;

    @ManyToOne
    private Adjudicataire adjudicataire;

    private LocalDate dateOrdreService;
    
    @ManyToMany(mappedBy = "projets")
    private Set<Utilisateur> utilisateurs = new HashSet<>();


    private int duree; // en jours

    private double tauxPhysique;

    private String observation;

    private double latitude;

    private double longitude;
}


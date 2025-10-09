package Gestion.Employeer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "commentaires")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenu;

    private String photoUrl;

    private LocalDateTime dateCommentaire;

    @ManyToOne
    private Utilisateur auteur;

    @ManyToOne
    private Projet projet;
}

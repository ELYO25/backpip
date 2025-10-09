package Gestion.Employeer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "utilisateurs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomComplet;

    @Column(unique = true, nullable = false)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "mot_de_passe", nullable = false)
    private String password;

    /** 🔹 Nouveau: lien vers l’entité Role */
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private Role role;

    // Rattachements éventuels “profil”
    @ManyToOne private Ministere ministere;
    @ManyToOne private Region region;
    @ManyToOne private District district;

    // Missions
    @ManyToMany
    @JoinTable(
        name = "missions_utilisateur_regions",
        joinColumns = @JoinColumn(name = "utilisateur_id"),
        inverseJoinColumns = @JoinColumn(name = "region_id"))
    private Set<Region> regionsAssignes = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "missions_utilisateur_districts",
        joinColumns = @JoinColumn(name = "utilisateur_id"),
        inverseJoinColumns = @JoinColumn(name = "district_id"))
    private Set<District> districtsAssignes = new HashSet<>();

    // Si tu utilises la relation d’affectation directe aux projets :
    @ManyToMany
    @JoinTable(
        name = "utilisateurs_projets",
        joinColumns = @JoinColumn(name = "utilisateur_id"),
        inverseJoinColumns = @JoinColumn(name = "projet_id"))
    private Set<Projet> projets = new HashSet<>();

    // Security
 // ...
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // simple: LEVEL_1 .. LEVEL_5
        int lvl = (role != null ? role.getNiveau() : 0);
        return List.of(new SimpleGrantedAuthority("LEVEL_" + lvl));
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}

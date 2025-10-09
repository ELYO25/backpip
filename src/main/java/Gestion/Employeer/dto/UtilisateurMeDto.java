package Gestion.Employeer.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UtilisateurMeDto {
    private Long id;
    private String nomComplet;
    private String username;
    private RoleDto role;
}

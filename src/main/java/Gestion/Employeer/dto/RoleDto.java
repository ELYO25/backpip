package Gestion.Employeer.dto;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RoleDto {
    private Long id;
    private String nom;
    private int niveau;
}

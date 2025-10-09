// src/main/java/Gestion/Employeer/dto/MeResponse.java
package Gestion.Employeer.dto;

import Gestion.Employeer.model.Utilisateur;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class MeResponse {
    private Long id;
    private String username;
    private String nomComplet;

    private Long roleId;
    private String roleNom;
    private Integer roleNiveau;

    private Long ministereId;
    private Long regionId;
    private Long districtId;

    public static MeResponse from(Utilisateur u) {
        return MeResponse.builder()
                .id(u.getId())
                .username(u.getUsername())
                .nomComplet(u.getNomComplet())
                .roleId(u.getRole() != null ? u.getRole().getId() : null)
                .roleNom(u.getRole() != null ? u.getRole().getNom() : null)
                .roleNiveau(u.getRole() != null ? u.getRole().getNiveau() : null)
                .ministereId(u.getMinistere() != null ? u.getMinistere().getId() : null)
                .regionId(u.getRegion() != null ? u.getRegion().getId() : null)
                .districtId(u.getDistrict() != null ? u.getDistrict().getId() : null)
                .build();
    }
}

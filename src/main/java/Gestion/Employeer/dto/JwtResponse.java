package Gestion.Employeer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";

    // ✅ constructeur supplémentaire
    public JwtResponse(String token) {
        this.token = token;
        this.type = "Bearer";
    }
}

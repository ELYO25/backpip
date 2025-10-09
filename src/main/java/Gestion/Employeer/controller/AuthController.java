package Gestion.Employeer.controller;

import Gestion.Employeer.config.JwtTokenProvider;
import Gestion.Employeer.dto.RoleDto;
import Gestion.Employeer.dto.UtilisateurMeDto;
import Gestion.Employeer.model.Utilisateur;
import Gestion.Employeer.repositories.UtilisateurRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwt;
    private final UtilisateurRepository utilisateurRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var auth = new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        authManager.authenticate(auth); // lève une exception si KO
        String token = jwt.generateToken(req.getUsername());
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @GetMapping("/me")
    public ResponseEntity<UtilisateurMeDto> me(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof Utilisateur user)) {
            return ResponseEntity.status(401).build();
        }
        var role = user.getRole(); // ManyToOne => EAGER par défaut, pas de problème de lazy ici
        var dto = UtilisateurMeDto.builder()
                .id(user.getId())
                .nomComplet(user.getNomComplet())
                .username(user.getUsername())
                .role(role == null ? null : RoleDto.builder()
                        .id(role.getId())
                        .nom(role.getNom())
                        .niveau(role.getNiveau())
                        .build())
                .build();
        return ResponseEntity.ok(dto);
    }

    @Data
    static class LoginRequest { private String username; private String password; }
    @Data
    static class TokenResponse { private final String token; }
}

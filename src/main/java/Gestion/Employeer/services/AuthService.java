package Gestion.Employeer.services;

import Gestion.Employeer.config.JwtTokenProvider;
import Gestion.Employeer.dto.LoginRequest;
import Gestion.Employeer.dto.RegisterRequest;
import Gestion.Employeer.dto.JwtResponse;
import Gestion.Employeer.model.Role;
import Gestion.Employeer.model.Utilisateur;
import Gestion.Employeer.repositories.RoleRepository;
import Gestion.Employeer.repositories.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        String token = jwtTokenProvider.generateToken(loginRequest.getUsername());
        return new JwtResponse(token);
    }

    public Utilisateur register(RegisterRequest registerRequest) {
        if (utilisateurRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Nom d'utilisateur déjà pris");
        }

        Role roleN1 = roleRepository.findFirstByNiveau(1)
            .orElseThrow(() -> new RuntimeException("Aucun rôle de niveau 1 n'est défini"));

        Utilisateur user = Utilisateur.builder()
            .username(registerRequest.getUsername())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .nomComplet(registerRequest.getNomComplet())
            .role(roleN1)
            .build();

        return utilisateurRepository.save(user);
    }
}

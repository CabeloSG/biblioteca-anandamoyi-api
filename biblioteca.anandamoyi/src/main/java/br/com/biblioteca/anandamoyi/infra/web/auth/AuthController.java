package br.com.biblioteca.anandamoyi.infra.web.auth;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.infra.security.JwtService;
import br.com.biblioteca.anandamoyi.infra.web.dto.LoginRequestDTO;
import br.com.biblioteca.anandamoyi.infra.web.dto.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(
            @Valid @RequestBody LoginRequestDTO request
    ) {

        Usuario usuario = usuarioRepository.buscarPorEmail(request.email())
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Usuário inexistente ou senha inválida"
                        )
                );

        if (!passwordEncoder.matches(
                request.senha(),
                usuario.getSenhaHash()
        )) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Usuário inexistente ou senha inválida"
            );
        }

        String token = jwtService.gerarToken(usuario);

        return new LoginResponseDTO(token);
    }
}
package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.infra.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AutenticarUsuarioUseCaseImpl {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AutenticarUsuarioUseCaseImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String autenticar(String email, String senha) {

        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Credenciais inválidas")
                );

        if (!passwordEncoder.matches(senha, usuario.getSenhaHash())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return jwtService.gerarToken(usuario);
    }
}


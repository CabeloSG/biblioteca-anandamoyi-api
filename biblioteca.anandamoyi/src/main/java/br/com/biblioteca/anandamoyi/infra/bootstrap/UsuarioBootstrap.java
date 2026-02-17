package br.com.biblioteca.anandamoyi.infra.bootstrap;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioBootstrap implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioBootstrap(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        criarUsuarioSeNaoExistir(
                "Administrador",
                "admin@biblioteca.com",
                "123456",
                Role.ADMIN
        );

        criarUsuarioSeNaoExistir(
                "Bibliotecário",
                "bibliotecario@biblioteca.com",
                "123456",
                Role.BIBLIOTECARIO
        );

        criarUsuarioSeNaoExistir(
                "Leitor",
                "leitor@biblioteca.com",
                "123456",
                Role.LEITOR
        );
    }

    private void criarUsuarioSeNaoExistir(
            String nome,
            String email,
            String senha,
            Role role
    ) {
        if (usuarioRepository.buscarPorEmail(email).isPresent()) {
            return;
        }

        Usuario usuario = Usuario.criar(
                nome,
                email,
                passwordEncoder.encode(senha),
                role
        );

        usuarioRepository.salvar(usuario);

        System.out.println("✔ Usuário criado: " + email + " (" + role + ")");
    }
}

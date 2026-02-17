package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CriarUsuarioUseCaseImpl implements CriarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public CriarUsuarioUseCaseImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Usuario executar(CriarUsuarioCommand command) {

        usuarioRepository.buscarPorEmail(command.email())
                .ifPresent(u -> {
                    throw new IllegalArgumentException("Email já cadastrado");
                });

        String senhaHash = passwordEncoder.encode(command.senha());
        Usuario usuario = new Usuario(
                null,
                command.nome(),
                command.email(),
                passwordEncoder.encode(command.senha()),
                command.role(),
                command.ativo()
        );

        return usuarioRepository.salvar(usuario);
    }
}

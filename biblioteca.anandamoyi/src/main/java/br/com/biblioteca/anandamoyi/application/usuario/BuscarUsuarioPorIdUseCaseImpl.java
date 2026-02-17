package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BuscarUsuarioPorIdUseCaseImpl implements BuscarUsuarioPorIdUseCase {

    private final UsuarioRepository usuarioRepository;

    public BuscarUsuarioPorIdUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario executar(Long id) {
        return usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
    }
}


package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.application.usuario.ListarUsuarioUseCase;
import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarUsuarioUseCaseImpl implements ListarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public ListarUsuarioUseCaseImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> executar() {

        return usuarioRepository.findAll();
    }

}
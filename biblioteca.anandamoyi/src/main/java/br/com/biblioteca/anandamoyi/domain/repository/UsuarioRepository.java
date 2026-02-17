package br.com.biblioteca.anandamoyi.domain.repository;

import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorId(Long id);
}

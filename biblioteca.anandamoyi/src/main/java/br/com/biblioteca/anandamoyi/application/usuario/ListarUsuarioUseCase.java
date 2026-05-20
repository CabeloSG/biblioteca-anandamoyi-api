package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;

import java.util.List;

public interface ListarUsuarioUseCase {

    List<Usuario> executar();

}

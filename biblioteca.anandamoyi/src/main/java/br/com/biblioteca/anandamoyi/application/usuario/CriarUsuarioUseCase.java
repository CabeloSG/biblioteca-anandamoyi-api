package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;

public interface CriarUsuarioUseCase {
        Usuario executar(CriarUsuarioCommand command);
}
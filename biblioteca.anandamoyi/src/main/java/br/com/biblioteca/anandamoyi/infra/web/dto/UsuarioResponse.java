package br.com.biblioteca.anandamoyi.infra.web.dto;

import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;

public record UsuarioResponse(
            Long id,
            String nome,
            String email,
            Role role,
            Boolean ativo
    ) {

    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.isAtivo()
        );
    }

}


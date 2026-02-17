package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.enums.Role;

public record UsuarioResponseDTO (
            Long id,
            String nome,
            String email,
            Role role,
            Boolean ativo
    ) {}


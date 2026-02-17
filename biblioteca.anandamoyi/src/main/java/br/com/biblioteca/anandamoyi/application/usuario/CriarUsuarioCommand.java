package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.enums.Role;

public record CriarUsuarioCommand(
        String nome,
        String email,
        String senha,
        Role role,
        boolean ativo
) {}
package br.com.biblioteca.anandamoyi.infra.web.dto;

public record LoginRequestDTO(
        String email,
        String senha
) {
}

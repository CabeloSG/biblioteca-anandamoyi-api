package br.com.biblioteca.anandamoyi.application.usuario;

public record CriarUsuarioRequest(
        String nome,
        String email,
        String senha,
        String role,
        boolean ativo
) {
}

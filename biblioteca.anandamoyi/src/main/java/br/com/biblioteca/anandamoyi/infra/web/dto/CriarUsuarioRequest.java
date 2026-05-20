package br.com.biblioteca.anandamoyi.infra.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarUsuarioRequest(

        @NotBlank(message = "Nome obrigatório")
        String nome,

        @Email(message = "Email inválido")
        @NotBlank(message = "Email obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatória")
        @Size(min = 6, message = "Senha deve ter pelo menos 6 caracteres")
        String senha,

        @NotBlank(message = "Role obrigatória")
        String role,

        boolean ativo
) {
}
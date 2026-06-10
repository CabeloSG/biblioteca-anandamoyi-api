package br.com.biblioteca.anandamoyi.application.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CriarLivroRequest(

        @NotBlank(message = "Título obrigatório")
        String titulo,

        @NotBlank(message = "Autor obrigatório")
        String autor,

        @NotBlank(message = "Código BN obrigatório")
        String codigoBN,

        @NotBlank(message = "ISBN obrigatório")
        String isbn,

        String imagemUrl,

        @NotBlank(message = "Edição obrigatória")
        String edicao,

        @Min(value = 1, message = "Quantidade deve ser maior que zero")
        int quantidadeExemplares
) {
}
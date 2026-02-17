package br.com.biblioteca.anandamoyi.application.dto;

public record CriarLivroRequest(
        String titulo,
        String autor,
        String codigoBN,
        String isbn,
        String edicao,
        int quantidadeExemplares
) {
}

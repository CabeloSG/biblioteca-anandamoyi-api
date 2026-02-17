package br.com.biblioteca.anandamoyi.application.dto;

public record EditarLivroRequest(
        String titulo,
        String autor,
        String codigoBN,
        String isbn,
        Integer edicao
) {
}

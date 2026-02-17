package br.com.biblioteca.anandamoyi.infra.web.livro;

public record LivroRequest(
        String titulo,
        String autor,
        String isbn
) {}

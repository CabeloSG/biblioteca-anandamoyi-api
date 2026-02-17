package br.com.biblioteca.anandamoyi.infra.web.livro;

public record LivroResponse(
        Long id,
        String titulo,
        String autor,
        String isbn
) {}


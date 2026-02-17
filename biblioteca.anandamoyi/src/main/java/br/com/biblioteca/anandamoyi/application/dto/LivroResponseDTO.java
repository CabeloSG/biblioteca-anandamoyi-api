package br.com.biblioteca.anandamoyi.application.dto;

import br.com.biblioteca.anandamoyi.domain.entity.Livro;

public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigoBN;

    public LivroResponseDTO(
            Long id,
            String titulo,
            String autor,
            String codigoBN
    ) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.codigoBN = codigoBN;
    }

    public static LivroResponseDTO from(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigoBN()
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigoBN() {
        return codigoBN;
    }
}

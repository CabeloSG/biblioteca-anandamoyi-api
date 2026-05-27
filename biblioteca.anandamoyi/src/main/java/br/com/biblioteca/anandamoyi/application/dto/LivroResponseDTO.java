package br.com.biblioteca.anandamoyi.application.dto;

import br.com.biblioteca.anandamoyi.domain.entity.Livro;

public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String codigoBN;

    // OPCIONAIS
    private String isbn;
    private Integer edicao;
    private Integer quantidadeExemplares;

    public LivroResponseDTO(
            Long id,
            String titulo,
            String autor,
            String codigoBN,
            String isbn,
            Integer edicao,
            Integer quantidadeExemplares
    ) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.codigoBN = codigoBN;
        this.isbn = isbn;
        this.edicao = edicao;
        this.quantidadeExemplares = quantidadeExemplares;
    }

    public static LivroResponseDTO from(Livro livro) {

        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigoBN(),
                livro.getIsbn(),
                livro.getEdicao(),
                livro.getQuantidadeExemplares()
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

    public String getIsbn() {
        return isbn;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public Integer getQuantidadeExemplares() {
        return quantidadeExemplares;
    }
}
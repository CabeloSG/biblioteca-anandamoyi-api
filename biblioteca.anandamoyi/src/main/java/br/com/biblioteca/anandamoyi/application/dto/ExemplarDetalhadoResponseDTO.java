package br.com.biblioteca.anandamoyi.application.dto;

import java.time.LocalDate;

public class ExemplarDetalhadoResponseDTO {

    private final Long id;
    private final boolean disponivel;
    private final LocalDate dataDevolucaoPrevista;
    private final Long edicaoId;
    private final String titulo;
    private final String autor;

    public ExemplarDetalhadoResponseDTO(
            Long id,
            boolean disponivel,
            LocalDate dataDevolucaoPrevista,
            Long edicaoId,
            String titulo,
            String autor
    ) {
        this.id = id;
        this.disponivel = disponivel;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.edicaoId = edicaoId;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Long getId() {
        return id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public Long getEdicaoId() {
        return edicaoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}
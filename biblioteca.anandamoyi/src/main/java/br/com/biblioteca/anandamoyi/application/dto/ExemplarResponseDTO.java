package br.com.biblioteca.anandamoyi.application.dto;

import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity;

import java.time.LocalDate;

public class ExemplarResponseDTO {

    private Long id;
    private boolean disponivel;
    private LocalDate dataDevolucaoPrevista;

    private Long edicaoId;
    private String livroTitulo;
    private String livroAutor;

    public ExemplarResponseDTO(
            Long id,
            boolean disponivel,
            LocalDate dataDevolucaoPrevista,
            Long edicaoId,
            String livroTitulo,
            String livroAutor
    ) {
        this.id = id;
        this.disponivel = disponivel;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.edicaoId = edicaoId;
        this.livroTitulo = livroTitulo;
        this.livroAutor = livroAutor;
    }

    public static ExemplarResponseDTO from(ExemplarLivro exemplar, Livro livro) {
        return new ExemplarResponseDTO(
                exemplar.getId(),
                exemplar.isDisponivel(),
                exemplar.getDataDevolucaoPrevista(),
                exemplar.getEdicao().getId(),
                livro.getTitulo(),
                livro.getAutor()
        );
    }

    public static ExemplarResponseDTO from(ExemplarLivroEntity exemplar) {

        return new ExemplarResponseDTO(
                exemplar.getId(),
                exemplar.isDisponivel(),
                exemplar.getDataDevolucaoPrevista(),
                exemplar.getEdicao().getId(),
                exemplar.getEdicao().getLivro().getTitulo(),
                exemplar.getEdicao().getLivro().getAutor()
        );
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

    public String getLivroTitulo() {
        return livroTitulo;
    }

    public String getLivroAutor() {
        return livroAutor;
    }
}

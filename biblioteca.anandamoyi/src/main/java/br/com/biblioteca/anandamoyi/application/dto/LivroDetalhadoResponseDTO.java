package br.com.biblioteca.anandamoyi.application.dto;

import br.com.biblioteca.anandamoyi.application.mapper.ExemplarResponseMapper;
import br.com.biblioteca.anandamoyi.application.mapper.ExemplarResumoMapper;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;

import java.util.List;

public class LivroDetalhadoResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private List<EdicaoResponseDTO> edicoes;

    public LivroDetalhadoResponseDTO(
            Long id,
            String titulo,
            String autor,
            List<EdicaoResponseDTO> edicoes
    ) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.edicoes = edicoes;
    }

    // 🔹 FACTORY METHOD
    public static LivroDetalhadoResponseDTO from(Livro livro) {

        return new LivroDetalhadoResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getEdicoes().stream()
                        .map(edicao -> new EdicaoResponseDTO(
                                edicao.getId(),
                                edicao.getIsbn(),
                                edicao.getEdicao(),
                                edicao.getExemplares().stream()
                                        .map(ExemplarResumoResponseDTO::from)
                                        //.map(ExemplarResumoMapper::toResumoDTO)
                                        .toList()
                        ))
                        .toList()
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

    public List<EdicaoResponseDTO> getEdicoes() {
        return edicoes;
    }
}

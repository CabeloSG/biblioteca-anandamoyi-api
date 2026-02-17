package br.com.biblioteca.anandamoyi.application.dto;

import java.util.List;

public class EdicaoResponseDTO {

    private Long id;
    private String isbn;
    private String edicao;
    //private List<ExemplarResponseDTO> exemplares;
    private List<ExemplarResumoResponseDTO> exemplares;

    public EdicaoResponseDTO(
            Long id,
            String isbn,
            String edicao,
            //List<ExemplarResponseDTO> exemplares
            List<ExemplarResumoResponseDTO> exemplares
    ) {
        this.id = id;
        this.isbn = isbn;
        this.edicao = edicao;
        this.exemplares = exemplares;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEdicao() {
        return edicao;
    }

    //public List<ExemplarResponseDTO> getExemplares() {
        //return exemplares;
   // }

    public List<ExemplarResumoResponseDTO> getExemplares() {
        return exemplares;
    }
}

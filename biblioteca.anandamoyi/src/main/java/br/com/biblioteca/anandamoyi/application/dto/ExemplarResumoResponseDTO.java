package br.com.biblioteca.anandamoyi.application.dto;

import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ExemplarResumoResponseDTO {

    private Long id;
    private boolean disponivel;
    private LocalDate dataDevolucaoPrevista;

    // 🔹 Construtor padrão (necessário para Jackson em alguns cenários)
    protected ExemplarResumoResponseDTO() {
    }

    @JsonCreator
    public ExemplarResumoResponseDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("disponivel") boolean disponivel,
            @JsonProperty("dataDevolucaoPrevista") LocalDate dataDevolucaoPrevista
    ) {
        this.id = id;
        this.disponivel = disponivel;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public static ExemplarResumoResponseDTO from(ExemplarLivro exemplar) {
        return new ExemplarResumoResponseDTO(
                exemplar.getId(),
                exemplar.isDisponivel(),
                exemplar.getDataDevolucaoPrevista()
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

}

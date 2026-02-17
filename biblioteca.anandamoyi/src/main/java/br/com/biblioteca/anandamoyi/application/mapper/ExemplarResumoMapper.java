package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.application.dto.ExemplarResumoResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;

public class ExemplarResumoMapper {

    private ExemplarResumoMapper() {}

    public static ExemplarResumoResponseDTO toResumoDTO(
            ExemplarLivro exemplar
    ) {
        return new ExemplarResumoResponseDTO(
                exemplar.getId(),
                exemplar.isDisponivel(),
                exemplar.getDataDevolucaoPrevista()
        );
    }
}

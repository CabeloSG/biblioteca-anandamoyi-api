package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.application.dto.ExemplarResponseDTO;
import br.com.biblioteca.anandamoyi.application.dto.ExemplarResumoResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;

public class ExemplarResponseMapper {

    private ExemplarResponseMapper() {}

    /**
     * DTO COMPLETO
     * Usado apenas em casos como:
     * - emprestar exemplar
     * - devolver exemplar
     */
    public static ExemplarResponseDTO toResponseDTO(ExemplarLivro exemplar) {

        if (exemplar == null) {
            return null;
        }

        return new ExemplarResponseDTO(
                exemplar.getId(),
                exemplar.isDisponivel(),
                exemplar.getDataDevolucaoPrevista(),
                exemplar.getEdicao().getId(),
                exemplar.getEdicao().getLivro().getTitulo(),
                exemplar.getEdicao().getLivro().getAutor()
        );
    }

    /**
     * DTO RESUMO
     * Usado em:
     * - LivroDetalhadoResponseDTO
     * - listagens
     */
    public static ExemplarResumoResponseDTO toResumoDTO(ExemplarLivro exemplar) {

        if (exemplar == null) {
            return null;
        }

        return new ExemplarResumoResponseDTO(
                exemplar.getId(),
                exemplar.isDisponivel(),
                exemplar.getDataDevolucaoPrevista()
        );
    }
}

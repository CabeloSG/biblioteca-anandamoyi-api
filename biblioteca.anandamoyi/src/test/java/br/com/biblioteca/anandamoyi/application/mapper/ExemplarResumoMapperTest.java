package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.EdicaoLivro;
import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExemplarResumoMapperTest {

    @Test
    void deveConverterParaResumo() {

        EdicaoLivro edicao = new EdicaoLivro("ISBN", "1", null);

        ExemplarLivro exemplar = new ExemplarLivro(edicao);
        exemplar.setId(10L);

        var dto = ExemplarResumoMapper.toResumoDTO(exemplar);

        assertNotNull(dto);
        assertEquals(10L, dto.getId());
    }
}
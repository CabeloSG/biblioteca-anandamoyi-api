package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExemplarResponseMapperTest {

    @Test
    void deveConverterParaResponseDTO() {

        Livro livro = new Livro(1L, "Clean Code", "Robert", "123");

        EdicaoLivro edicao = new EdicaoLivro("ISBN", "1", livro);
        edicao.setId(10L);

        ExemplarLivro exemplar = new ExemplarLivro(edicao);
        exemplar.setId(100L);

        var dto = ExemplarResponseMapper.toResponseDTO(exemplar);

        assertNotNull(dto);
        assertEquals(100L, dto.getId());
        assertEquals(10L, dto.getEdicaoId());
    }

    @Test
    void deveRetornarNullQuandoExemplarNull() {

        var dto = ExemplarResponseMapper.toResponseDTO(null);

        assertNull(dto);
    }

    @Test
    void deveConverterResumoDTO() {

        EdicaoLivro edicao = new EdicaoLivro("ISBN", "1", null);

        ExemplarLivro exemplar = new ExemplarLivro(edicao);
        exemplar.setId(1L);

        var dto = ExemplarResponseMapper.toResumoDTO(exemplar);

        assertEquals(1L, dto.getId());
    }
}
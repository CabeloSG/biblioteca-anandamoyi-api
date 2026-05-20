package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivrosMapperTest {

    @Test
    void deveConverterLivroParaResponseDTO() {

        Livro livro = new Livro(1L, "Clean Code", "Robert", "123");

        var dto = LivroMapper.toResponse(livro);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals("Clean Code", dto.getTitulo());
    }

    @Test
    void deveConverterLivroResponseMapper() {

        Livro livro = new Livro(2L, "DDD", "Evans", "999");

        var dto = LivroResponseMapper.from(livro);

        assertEquals("DDD", dto.getTitulo());
        assertEquals("Evans", dto.getAutor());
    }
}
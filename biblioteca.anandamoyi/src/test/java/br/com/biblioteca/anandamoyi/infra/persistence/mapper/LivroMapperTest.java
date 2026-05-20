package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.application.dto.LivroResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroMapperTest {

    @Test
    void deveConverterLivroParaResponseDTO() {

        Livro livro = new Livro(
                1L,
                "Clean Code",
                "Robert Martin",
                "123456789"
        );

        LivroResponseDTO dto = LivroMapper.toResponse(livro);

        assertNotNull(dto);
        assertEquals(livro.getId(), dto.getId());
        assertEquals(livro.getTitulo(), dto.getTitulo());
        assertEquals(livro.getAutor(), dto.getAutor());
        assertEquals(livro.getCodigoBN(), dto.getCodigoBN());
    }
}
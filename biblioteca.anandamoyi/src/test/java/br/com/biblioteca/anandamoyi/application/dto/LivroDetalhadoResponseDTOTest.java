package br.com.biblioteca.anandamoyi.application.dto;

import br.com.biblioteca.anandamoyi.domain.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroDetalhadoResponseDTOTest {

    @Test
    void deveConverterLivroParaDTO() {

        Livro livro = new Livro(1L, "Clean Code", "Robert", "123");

        EdicaoLivro edicao = new EdicaoLivro("ISBN123", "1ª edição", livro);
        edicao.setId(10L);

        ExemplarLivro exemplar = new ExemplarLivro(edicao);
        exemplar.setId(100L);

        edicao.adicionarExemplar(exemplar);
        livro.adicionarEdicao(edicao);

        var dto = LivroDetalhadoResponseDTO.from(livro);

        assertNotNull(dto);
        assertEquals(1L, dto.getId());
        assertEquals(1, dto.getEdicoes().size());
    }
}
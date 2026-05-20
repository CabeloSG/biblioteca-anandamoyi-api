package br.com.biblioteca.anandamoyi.infra.persistence.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroEntityMapperTest {

    @Test
    void deveConverterDomainParaEntity() {

        Livro livro = new Livro(1L, "Clean Code", "Robert", "123");

        EdicaoLivro edicao = new EdicaoLivro("ISBN", "1", livro);
        edicao.setId(10L);

        ExemplarLivro exemplar = new ExemplarLivro(edicao);
        exemplar.setId(100L);

        edicao.adicionarExemplar(exemplar);
        livro.adicionarEdicao(edicao);

        var entity = LivroEntityMapper.toEntity(livro);

        assertNotNull(entity);
        assertEquals(1, entity.getEdicoes().size());
    }

    @Test
    void deveConverterEntityParaDomain() {

        var entity = new br.com.biblioteca.anandamoyi.infra.persistence.entity.LivroEntity();
        entity.setId(1L);
        entity.setTitulo("Clean Code");
        entity.setAutor("Robert");
        entity.setCodigoBN("123");

        var domain = LivroEntityMapper.toDomain(entity);

        assertEquals("Clean Code", domain.getTitulo());
    }
}
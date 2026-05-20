package br.com.biblioteca.anandamoyi.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroTest {

    @Test
    void deveAdicionarEdicao() {

        Livro livro = new Livro("Titulo", "Autor", "123");

        EdicaoLivro edicao = new EdicaoLivro("isbn", "1 ed", livro);

        livro.adicionarEdicao(edicao);

        assertFalse(livro.getEdicoes().isEmpty());
    }

    @Test
    void deveBuscarExemplarPorId() {

        Livro livro = new Livro("Titulo", "Autor", "123");
        EdicaoLivro edicao = new EdicaoLivro("isbn", "1 ed", livro);
        edicao.setId(1L);

        ExemplarLivro exemplar = new ExemplarLivro(edicao);
        exemplar.setId(10L);

        edicao.adicionarExemplar(exemplar);
        livro.adicionarEdicao(edicao);

        ExemplarLivro resultado = livro.buscarExemplarPorId(10L);

        assertNotNull(resultado);
    }

    @Test
    void deveLancarErroSeExemplarNaoEncontrado() {

        Livro livro = new Livro("Titulo", "Autor", "123");

        assertThrows(RuntimeException.class,
                () -> livro.buscarExemplarPorId(1L));
    }
}
package br.com.biblioteca.anandamoyi.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExemplarLivroTest {

    @Test
    void deveEmprestarExemplar() {

        EdicaoLivro edicao = new EdicaoLivro("isbn", "1 ed", null);
        ExemplarLivro exemplar = new ExemplarLivro(edicao);

        exemplar.emprestar(7);

        assertFalse(exemplar.isDisponivel());
        assertNotNull(exemplar.getDataEmprestimo());
    }

    @Test
    void deveLancarErroSeJaEmprestado() {

        EdicaoLivro edicao = new EdicaoLivro("isbn", "1 ed", null);
        ExemplarLivro exemplar = new ExemplarLivro(edicao);

        exemplar.emprestar(7);

        assertThrows(IllegalStateException.class,
                () -> exemplar.emprestar(7));
    }

    @Test
    void deveDevolverExemplar() {

        EdicaoLivro edicao = new EdicaoLivro("isbn", "1 ed", null);
        ExemplarLivro exemplar = new ExemplarLivro(edicao);

        exemplar.emprestar(7);
        exemplar.devolver();

        assertTrue(exemplar.isDisponivel());
    }
}
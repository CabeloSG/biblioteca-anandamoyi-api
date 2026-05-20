package br.com.biblioteca.anandamoyi.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LivroExtraTest {

    @Test
    void deveEditarLivro() {

        Livro livro = new Livro(1L, "A", "B", "123");

        livro.editar("Novo", "Autor2", "999", "ISBN", 2);

        assertEquals("Novo", livro.getTitulo());
        assertEquals("Autor2", livro.getAutor());
    }

    @Test
    void deveBuscarExemplarPorIdErro() {

        Livro livro = new Livro(1L, "A", "B", "123");

        assertThrows(RuntimeException.class,
                () -> livro.buscarExemplarPorId(999L));
    }
}
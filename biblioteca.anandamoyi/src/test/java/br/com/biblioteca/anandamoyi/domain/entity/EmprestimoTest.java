package br.com.biblioteca.anandamoyi.domain.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoTest {

    @Test
    void deveRegistrarDevolucaoSemMulta() {

        Emprestimo emprestimo = new Emprestimo(1L, 1L);

        emprestimo.registrarDevolucao(
                emprestimo.getDataPrevistaDevolucao()
        );

        assertTrue(emprestimo.isDevolvido());
        assertEquals(0.0, emprestimo.getMulta());
    }

    @Test
    void deveGerarMultaQuandoAtrasado() {

        Emprestimo emprestimo = new Emprestimo(1L, 1L);

        LocalDate dataAtrasada =
                emprestimo.getDataPrevistaDevolucao().plusDays(3);

        emprestimo.registrarDevolucao(dataAtrasada);

        assertTrue(emprestimo.getMulta() > 0);
    }

    @Test
    void naoDevePermitirDevolucaoDuplicada() {

        Emprestimo emprestimo = new Emprestimo(1L, 1L);

        emprestimo.registrarDevolucao(LocalDate.now());

        assertThrows(IllegalStateException.class,
                () -> emprestimo.registrarDevolucao(LocalDate.now()));
    }
}
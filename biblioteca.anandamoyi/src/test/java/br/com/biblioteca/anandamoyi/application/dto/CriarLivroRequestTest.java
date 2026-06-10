package br.com.biblioteca.anandamoyi.application.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CriarLivroRequestTest {

    @Test
    void deveCriarRequest() {
        CriarLivroRequest dto = new CriarLivroRequest(
                "Titulo",
                "Autor",
                "123",
                "ISBN",
                "https://teste.com/capa.jpg",
                "1 ed",
                2
        );

        assertEquals("Titulo", dto.titulo());
        assertEquals(2, dto.quantidadeExemplares());
    }
}
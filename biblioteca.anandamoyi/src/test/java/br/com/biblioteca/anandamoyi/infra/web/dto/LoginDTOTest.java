package br.com.biblioteca.anandamoyi.infra.web.dto;

import br.com.biblioteca.anandamoyi.application.dto.CriarLivroRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginDTOTest {

    @Test
    void deveCriarLoginRequestDTO() {
        LoginRequestDTO dto = new LoginRequestDTO("email", "senha");

        assertEquals("email", dto.email());
        assertEquals("senha", dto.senha());
    }

    @Test
    void deveCriarLoginResponseDTO() {
        LoginResponseDTO dto = new LoginResponseDTO("token");

        assertEquals("token", dto.token());
    }

    @Test
    void deveCriarRequest() {

        CriarLivroRequest dto = new CriarLivroRequest(
                "Livro",
                "Autor",
                "123",
                "ISBN",
                "1ª",
                2
        );

        assertEquals("Livro", dto.titulo());
        assertEquals(2, dto.quantidadeExemplares());
    }

}
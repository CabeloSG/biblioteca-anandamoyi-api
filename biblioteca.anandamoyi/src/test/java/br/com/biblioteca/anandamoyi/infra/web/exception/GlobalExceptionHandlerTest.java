package br.com.biblioteca.anandamoyi.infra.web.exception;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void deveTratarIllegalArgumentException() {

        Map<String, Object> response =
                handler.handleIllegalArgument(
                        new IllegalArgumentException("erro teste")
                );

        assertEquals(400, response.get("status"));
        assertEquals("erro teste", response.get("message"));
        assertNotNull(response.get("timestamp"));
    }

    @Test
    void deveTratarRuntimeException() {

        var response =
                handler.handleRuntime(
                        new RuntimeException("erro runtime")
                );

        assertEquals(500, response.getStatusCode().value());
    }

    @Test
    void deveTratarIllegalStateException() {

        var response =
                handler.handleIllegalState(
                        new IllegalStateException("erro state")
                );

        assertEquals(409, response.getStatusCode().value());
    }

    @Test
    void deveTratarExemplarIndisponivel() {

        var response =
                handler.handleExemplarIndisponivel(
                        new br.com.biblioteca.anandamoyi.domain.exception.ExemplarInDisponivelException(1L)
                );

        assertEquals(409, response.getStatusCode().value());
    }

    @Test
    void deveTratarNotFound() {

        var response =
                handler.handleNotFound(
                        new br.com.biblioteca.anandamoyi.domain.exception.RecursoNaoEncontradoException("não encontrado")
                );

        assertEquals(404, response.getStatusCode().value());
    }
}
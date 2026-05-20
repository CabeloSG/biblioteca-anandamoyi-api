package br.com.biblioteca.anandamoyi.application.dto;

import br.com.biblioteca.anandamoyi.application.usuario.CriarUsuarioCommand;
import br.com.biblioteca.anandamoyi.domain.enums.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CriarUsuarioCommandTest {

    @Test
    void deveCriarCommand() {

        CriarUsuarioCommand command =
                new CriarUsuarioCommand(
                        "João",
                        "email",
                        "123",
                        Role.LEITOR,
                        true
                );

        assertEquals("João", command.nome());
        assertEquals("email", command.email());
    }
}
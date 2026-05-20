package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.domain.enums.Role;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ListarUsuarioUseCaseImplTest {

    private final UsuarioRepository repository = mock(UsuarioRepository.class);
    private final ListarUsuarioUseCaseImpl useCase =
            new ListarUsuarioUseCaseImpl(repository);

    @Test
    void deveListarUsuarios() {

        List<Usuario> usuariosMock = List.of(
                new Usuario(1L, "João", "email1", "senha", Role.LEITOR, true),
                new Usuario(2L, "Maria", "email2", "senha", Role.ADMIN, true)
        );

        when(repository.findAll()).thenReturn(usuariosMock);

        List<Usuario> resultado = useCase.executar();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void deveRetornarListaVazia() {
        when(repository.findAll()).thenReturn(List.of());

        var resultado = useCase.executar();

        assertTrue(resultado.isEmpty());
    }

}
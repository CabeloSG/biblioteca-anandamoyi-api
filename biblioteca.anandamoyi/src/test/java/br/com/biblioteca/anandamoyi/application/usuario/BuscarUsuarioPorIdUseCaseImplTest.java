package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarUsuarioPorIdUseCaseImplTest {

    private final UsuarioRepository repository = mock(UsuarioRepository.class);
    private final BuscarUsuarioPorIdUseCaseImpl useCase =
            new BuscarUsuarioPorIdUseCaseImpl(repository);

    @Test
    void deveBuscarUsuarioPorId() {

        Usuario usuario = new Usuario(
                1L, "João", "email", "senha", Role.LEITOR, true
        );

        when(repository.buscarPorId(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = useCase.executar(1L);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }

    @Test
    void deveLancarErroQuandoNaoEncontrar() {

        when(repository.buscarPorId(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> useCase.executar(1L));
    }

    @Test
    void deveLancarErroSeIdForNulo() {

        assertThrows(IllegalArgumentException.class, () -> useCase.executar(null));

    }

}
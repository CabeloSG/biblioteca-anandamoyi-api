package br.com.biblioteca.anandamoyi.infra.bootstrap;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioBootstrapTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioBootstrap bootstrap;

    @Test
    void naoDeveCriarUsuarioSeJaExistir() throws Exception {

        when(usuarioRepository.buscarPorEmail(any()))
                .thenReturn(Optional.of(mock(Usuario.class)));

        bootstrap.run();

        verify(usuarioRepository, never()).salvar(any());
    }

    @Test
    void deveCriarUsuariosQuandoNaoExistem() throws Exception {

        when(usuarioRepository.buscarPorEmail(any()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(any()))
                .thenReturn("senha");

        bootstrap.run();

        verify(usuarioRepository, atLeast(3)).salvar(any());
    }
}
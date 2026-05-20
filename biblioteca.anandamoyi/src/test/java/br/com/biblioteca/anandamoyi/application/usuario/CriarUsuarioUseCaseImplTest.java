package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarUsuarioUseCaseImplTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CriarUsuarioUseCaseImpl useCase;

    private CriarUsuarioCommand command;

    @BeforeEach
    void setup() {
        command = new CriarUsuarioCommand(
                "João",
                "joao@email.com",
                "123456",
                Role.LEITOR,
                true
        );
    }

    @Test
    void deveCriarUsuarioComSucesso() {

        when(usuarioRepository.buscarPorEmail(command.email()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(command.senha()))
                .thenReturn("senhaCriptografada");

        when(usuarioRepository.salvar(any(Usuario.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Usuario resultado = useCase.executar(command);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        assertEquals("senhaCriptografada", resultado.getSenhaHash());

        verify(usuarioRepository, times(1)).salvar(any(Usuario.class));
        verify(passwordEncoder, times(1)).encode(command.senha());
    }

    @Test
    void deveLancarExcecaoSeEmailJaExistir() {

        when(usuarioRepository.buscarPorEmail(command.email()))
                .thenReturn(Optional.of(
                        new Usuario(
                                1L,
                                "Teste",
                                "teste@email.com",
                                "senha",
                                Role.LEITOR,
                                true
                        )
                ));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(command)
        );

        assertEquals("Email já cadastrado", exception.getMessage());

        verify(usuarioRepository, never()).salvar(any());
        verify(passwordEncoder, never()).encode(any());
    }

    @Test
    void deveSalvarUsuarioComSenhaCriptografada() {

        when(usuarioRepository.buscarPorEmail(command.email()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(command.senha()))
                .thenReturn("senhaCriptografada");

        when(usuarioRepository.salvar(any(Usuario.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        useCase.executar(command);

        ArgumentCaptor<Usuario> captor = ArgumentCaptor.forClass(Usuario.class);

        verify(usuarioRepository).salvar(captor.capture());

        Usuario usuarioSalvo = captor.getValue();

        assertEquals("senhaCriptografada", usuarioSalvo.getSenhaHash());
    }

    @Test
    void deveLancarErroEmailDuplicado() {

        when(usuarioRepository.buscarPorEmail(anyString()))
                .thenReturn(Optional.of(
                        new Usuario(
                                1L,
                                "Teste",
                                "teste@email.com",
                                "senha",
                                Role.LEITOR,
                                true
                        )
                ));

        assertThrows(IllegalArgumentException.class, () ->
                useCase.executar(command)
        );

        verify(usuarioRepository, never()).salvar(any());
    }
}
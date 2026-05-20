package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.domain.exception.NenhumExemplarDisponivelException;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.*;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class EmprestarExemplarUseCaseTest {

    @Mock
    private ExemplarLivroJpaRepository exemplarRepository;

    @Mock
    private UsuarioJpaRepository usuarioRepository;

    @Mock
    private EmprestimoJpaRepository emprestimoRepository;

    @InjectMocks
    private EmprestarExemplarUseCase useCase;

    @Test
    void deveEmprestarComSucesso() {

        UsuarioEntity usuario = mock(UsuarioEntity.class);

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        ExemplarLivroEntity exemplar = mock(ExemplarLivroEntity.class);
        EdicaoLivroEntity edicao = mock(EdicaoLivroEntity.class);
        LivroEntity livro = mock(LivroEntity.class);

        when(exemplar.getEdicao()).thenReturn(edicao);
        when(edicao.getLivro()).thenReturn(livro);
        when(livro.isAtivo()).thenReturn(true);

        when(exemplarRepository.findFirstByEdicaoIdAndDisponivelTrue(1L))
                .thenReturn(Optional.of(exemplar));

        var response = useCase.executar(1L, 1L);

        assertNotNull(response);

        verify(usuario).validarPodeEmprestar();
        verify(exemplar).emprestar(any(), any());
        verify(emprestimoRepository).save(any());
    }

    @Test
    void deveLancarErroSeUsuarioNaoExiste() {

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class,
                () -> useCase.executar(1L, 1L));
    }

    @Test
    void deveLancarErroSemExemplarDisponivel() {

        UsuarioEntity usuario = mock(UsuarioEntity.class);

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(exemplarRepository.findFirstByEdicaoIdAndDisponivelTrue(1L))
                .thenReturn(Optional.empty());

        assertThrows(NenhumExemplarDisponivelException.class,
                () -> useCase.executar(1L, 1L));
    }

    @Test
    void deveLancarErroLivroInativo() {

        UsuarioEntity usuario = mock(UsuarioEntity.class);

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        ExemplarLivroEntity exemplar = mock(ExemplarLivroEntity.class);
        EdicaoLivroEntity edicao = mock(EdicaoLivroEntity.class);
        LivroEntity livro = mock(LivroEntity.class);

        when(exemplar.getEdicao()).thenReturn(edicao);
        when(edicao.getLivro()).thenReturn(livro);
        when(livro.isAtivo()).thenReturn(false);

        when(exemplarRepository.findFirstByEdicaoIdAndDisponivelTrue(1L))
                .thenReturn(Optional.of(exemplar));

        assertThrows(IllegalStateException.class,
                () -> useCase.executar(1L, 1L));
    }
}
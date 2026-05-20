package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.LivroDetalhadoResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuscarLivroPorIdUseCaseTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private BuscarLivroPorIdUseCase useCase;

    @Test
    void deveRetornarLivroQuandoEncontrado() {

        Livro livro = new Livro(
                1L,
                "Clean Code",
                "Robert Martin",
                "123"
        );

        when(repository.buscarPorId(1L))
                .thenReturn(Optional.of(livro));

        LivroDetalhadoResponseDTO response = useCase.executar(1L);

        assertNotNull(response);
        assertEquals("Clean Code", response.getTitulo());
    }

    @Test
    void deveLancarErroQuandoLivroNaoEncontrado() {

        when(repository.buscarPorId(1L))
                .thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> useCase.executar(1L)
        );

        assertEquals("Livro não encontrado", exception.getMessage());
    }
}
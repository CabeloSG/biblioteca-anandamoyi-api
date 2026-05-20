package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class ListarLivrosUseCaseTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private ListarLivrosUseCase useCase;

    @Test
    void deveListarLivros() {

        when(repository.listarTodos())
                .thenReturn(List.of(new Livro(1L, "Titulo", "Autor", "123")));

        var resultado = useCase.executar();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
    }
}
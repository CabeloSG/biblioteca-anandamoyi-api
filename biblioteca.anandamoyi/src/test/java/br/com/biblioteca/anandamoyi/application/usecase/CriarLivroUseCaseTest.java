package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.CriarLivroRequest;
import br.com.biblioteca.anandamoyi.application.dto.LivroResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.EdicaoLivro;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CriarLivroUseCaseTest {

    @Mock
    private LivroRepository repository;

    @InjectMocks
    private CriarLivroUseCase useCase;

    private CriarLivroRequest criarRequestValido() {
        return new CriarLivroRequest(
                "Clean Code",
                "Robert Martin",
                "123",
                "ISBN-123",
                "1",
                2
        );
    }

    @Test
    void deveCriarLivroComSucesso() {

        CriarLivroRequest request = criarRequestValido();

        when(repository.salvar(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        LivroResponseDTO response = useCase.executar(request);

        assertNotNull(response);
        assertEquals("Clean Code", response.getTitulo());

        verify(repository).salvar(any());
    }

    // 🔥 NOVO: garante que edições e exemplares foram criados
    @Test
    void deveCriarEdicaoEExemplaresCorretamente() {

        CriarLivroRequest request = criarRequestValido();

        ArgumentCaptor<Livro> captor = ArgumentCaptor.forClass(Livro.class);

        when(repository.salvar(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        useCase.executar(request);

        verify(repository).salvar(captor.capture());

        Livro livroSalvo = captor.getValue();

        assertEquals(1, livroSalvo.getEdicoes().size());

        EdicaoLivro edicao = livroSalvo.getEdicoes().get(0);

        assertEquals("ISBN-123", edicao.getIsbn());
        assertEquals("1", edicao.getEdicao());

        // 🔥 valida loop de exemplares
        assertEquals(2, edicao.getExemplares().size());
    }

    // 🔥 NOVO: garante retorno correto
    @Test
    void deveRetornarDTOComDadosCorretos() {

        CriarLivroRequest request = criarRequestValido();

        when(repository.salvar(any()))
                .thenAnswer(invocation -> {
                    Livro livro = invocation.getArgument(0);
                    return new Livro(
                            99L,
                            livro.getTitulo(),
                            livro.getAutor(),
                            livro.getCodigoBN()
                    );
                });

        LivroResponseDTO response = useCase.executar(request);

        assertEquals(99L, response.getId());
        assertEquals("Clean Code", response.getTitulo());
    }

    @Test
    void deveLancarErroQuandoQuantidadeInvalida() {
        CriarLivroRequest request = new CriarLivroRequest(
                "Clean Code", "Robert", "123", "ISBN", "1", 0
        );

        assertThrows(IllegalArgumentException.class,
                () -> useCase.executar(request));
    }

    @Test
    void deveLancarErroQuandoCodigoBNeISBNNulos() {
        CriarLivroRequest request = new CriarLivroRequest(
                "Clean Code", "Robert", null, null, "1", 1
        );

        assertThrows(IllegalArgumentException.class,
                () -> useCase.executar(request));
    }

    @Test
    void deveLancarErroQuandoEdicaoVazia() {
        CriarLivroRequest request = new CriarLivroRequest(
                "Clean Code", "Robert", "123", "ISBN", "", 1
        );

        assertThrows(IllegalArgumentException.class,
                () -> useCase.executar(request));
    }

    @Test
    void deveLancarErroQuandoIsbnVazio() {
        CriarLivroRequest request = new CriarLivroRequest(
                "Clean Code", "Robert", "123", "", "1", 1
        );

        assertThrows(IllegalArgumentException.class,
                () -> useCase.executar(request));
    }
}
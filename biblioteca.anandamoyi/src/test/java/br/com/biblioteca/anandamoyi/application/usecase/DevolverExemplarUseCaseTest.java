package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.domain.entity.Emprestimo;
import br.com.biblioteca.anandamoyi.domain.repository.EmprestimoRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.EdicaoLivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.LivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.ExemplarLivroJpaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@org.junit.jupiter.api.extension.ExtendWith(MockitoExtension.class)
class DevolverExemplarUseCaseTest {

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private ExemplarLivroJpaRepository exemplarRepository;

    @InjectMocks
    private DevolverExemplarUseCase useCase;

    @Test
    void deveDevolverExemplarComSucesso() {

        Emprestimo emprestimo = new Emprestimo(1L, 1L);

        when(emprestimoRepository.buscarPorId(1L))
                .thenReturn(Optional.of(emprestimo));

        // 🔥 MOCK COMPLETO (CORREÇÃO)
        ExemplarLivroEntity exemplar = mock(ExemplarLivroEntity.class);
        EdicaoLivroEntity edicao = mock(EdicaoLivroEntity.class);
        LivroEntity livro = mock(LivroEntity.class);

        when(exemplar.getEdicao()).thenReturn(edicao);
        when(edicao.getId()).thenReturn(1L);
        when(edicao.getLivro()).thenReturn(livro);

        when(exemplarRepository.findById(1L))
                .thenReturn(Optional.of(exemplar));

        var response = useCase.executar(1L);

        assertNotNull(response);

        verify(exemplar).devolver();
    }
}
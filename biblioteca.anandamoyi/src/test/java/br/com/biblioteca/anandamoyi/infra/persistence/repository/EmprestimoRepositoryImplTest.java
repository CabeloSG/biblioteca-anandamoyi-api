package br.com.biblioteca.anandamoyi.infra.persistence.repository;

import br.com.biblioteca.anandamoyi.domain.entity.Emprestimo;
import br.com.biblioteca.anandamoyi.infra.persistence.EmprestimoRepositoryImpl;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmprestimoRepositoryImplTest {

    private final EmprestimoJpaRepository jpaRepository = mock(EmprestimoJpaRepository.class);

    private final EmprestimoRepositoryImpl repository =
            new EmprestimoRepositoryImpl(jpaRepository);

    private Emprestimo criarEmprestimo() {
        return new Emprestimo(1L, 1L);
    }

    private EmprestimoEntity criarEntityMock() {

        ExemplarLivroEntity exemplar = new ExemplarLivroEntity();
        exemplar.setId(1L);

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(1L);

        EmprestimoEntity entity = new EmprestimoEntity();
        entity.setId(1L);
        entity.setExemplar(exemplar);
        entity.setLeitor(usuario);

        entity.setDevolvido(false);

        return entity;
    }

    @Test
    void deveSalvarEmprestimo() {

        when(jpaRepository.save(any(EmprestimoEntity.class)))
                .thenReturn(criarEntityMock());

        Emprestimo resultado = repository.salvar(criarEmprestimo());

        assertNotNull(resultado);
    }

    @Test
    void deveBuscarEmprestimoPorId() {

        when(jpaRepository.findById(1L))
                .thenReturn(Optional.of(criarEntityMock()));

        Optional<Emprestimo> resultado =
                repository.buscarPorId(1L);

        assertTrue(resultado.isPresent());
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarEmprestimo() {

        when(jpaRepository.findById(999L))
                .thenReturn(Optional.empty());

        Optional<Emprestimo> resultado =
                repository.buscarPorId(999L);

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveListarEmprestimosAtivos() {

        when(jpaRepository.findAll())
                .thenReturn(List.of(criarEntityMock()));

        List<Emprestimo> resultado =
                repository.listarAtivos();

        assertNotNull(resultado);
    }
}
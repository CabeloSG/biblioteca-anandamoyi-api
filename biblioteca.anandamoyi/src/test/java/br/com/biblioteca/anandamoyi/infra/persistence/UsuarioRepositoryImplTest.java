package br.com.biblioteca.anandamoyi.infra.persistence;

import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.UsuarioJpaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioRepositoryImplTest {

    @Mock
    private UsuarioJpaRepository jpaRepository;

    @InjectMocks
    private UsuarioRepositoryImpl repository;

    @Test
    void deveSalvarUsuario() {

        Usuario usuario = new Usuario(
                null,
                "João",
                "joao@email.com",
                "senha",
                Role.LEITOR,
                true
        );

        //  NÃO usa mapper aqui
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(1L);
        entity.setNome("João");
        entity.setEmail("joao@email.com");
        entity.setSenhaHash("senha");
        entity.setRole(Role.LEITOR);
        entity.setAtivo(true);

        when(jpaRepository.save(any(UsuarioEntity.class)))
                .thenReturn(entity);

        Usuario resultado = repository.salvar(usuario);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());

        verify(jpaRepository).save(any(UsuarioEntity.class));
    }

    @Test
    void deveBuscarPorEmail() {

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(1L);
        entity.setNome("Teste");
        entity.setEmail("teste@email.com");
        entity.setSenhaHash("senha");
        entity.setRole(Role.LEITOR);
        entity.setAtivo(true);

        when(jpaRepository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(entity));

        Optional<Usuario> resultado =
                repository.buscarPorEmail("teste@email.com");

        assertTrue(resultado.isPresent());
    }

    @Test
    void deveRetornarVazioQuandoNaoEncontrarEmail() {

        when(jpaRepository.findByEmail("nao@email.com"))
                .thenReturn(Optional.empty());

        Optional<Usuario> resultado =
                repository.buscarPorEmail("nao@email.com");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRetornarVazioQuandoEmailNaoExiste() {

        when(jpaRepository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        var resultado =
                repository.buscarPorEmail("naoexiste@email.com");

        assertTrue(resultado.isEmpty());
    }
}
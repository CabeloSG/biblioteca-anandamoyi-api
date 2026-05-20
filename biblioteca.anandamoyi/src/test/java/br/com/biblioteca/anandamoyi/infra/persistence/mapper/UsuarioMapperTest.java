package br.com.biblioteca.anandamoyi.infra.persistence.mapper;

import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    @Test
    void deveConverterParaEntity() {

        Usuario usuario = new Usuario(
                1L, "João", "email", "senha", Role.LEITOR, true
        );

        UsuarioEntity entity = UsuarioMapper.toEntity(usuario);

        assertNotNull(entity);
        assertEquals(usuario.getNome(), entity.getNome());
        assertEquals(usuario.getEmail(), entity.getEmail());
    }

    @Test
    void deveConverterParaDomain() {

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(1L);
        entity.setNome("João");
        entity.setEmail("email");

        Usuario usuario = UsuarioMapper.toDomain(entity);

        assertNotNull(usuario);
        assertEquals(entity.getNome(), usuario.getNome());
    }
}
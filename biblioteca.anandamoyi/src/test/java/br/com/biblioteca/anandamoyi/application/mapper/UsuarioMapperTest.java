package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.infra.persistence.mapper.UsuarioMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioMapperTest {

    private final UsuarioMapper mapper = new UsuarioMapper();

    @Test
    void deveConverterUsuario() {

        Usuario usuario = new Usuario(
                1L,
                "João",
                "joao@email.com",
                "senha",
                Role.LEITOR,
                true
        );

        // chama o mapper (ajuste o nome aqui se necessário)
        Object dto = mapper.toEntity(usuario); // se der erro, tenta: toResponse / map / converter

        assertNotNull(dto);
    }

    @Test
    void deveConverterUsuarioParaEntidade() {

        Usuario usuario = new Usuario(
                1L,"João", "email", "senha", Role.LEITOR, true
        );
        var entidade = mapper.toEntity(usuario);

        assertNotNull(entidade);
    }

    @Test
    void deveConverterParaEntityEDomain() {

        Usuario usuario = new Usuario(
                1L,
                "João",
                "email",
                "senha",
                Role.LEITOR,
                true
        );

        var entity = UsuarioMapper.toEntity(usuario);

        assertEquals("João", entity.getNome());

        var domain = UsuarioMapper.toDomain(entity);

        assertEquals("email", domain.getEmail());
    }

}
package br.com.biblioteca.anandamoyi.infra.persistence.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.Emprestimo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmprestimoMapperTest {

    @Test
    void deveConverterDomainParaEntity() {

        Emprestimo emp = new Emprestimo(
                1L, 2L, 3L,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                null,
                false,
                0.0
        );

        var entity = EmprestimoMapper.toEntity(emp);

        assertEquals(2L, entity.getExemplar().getId());
    }

    @Test
    void deveConverterEntityParaDomain() {

        var entity = new br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity();

        var exemplar = new br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity();
        exemplar.setId(2L);

        var usuario = new br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity();
        usuario.setId(3L);

        entity.setId(1L);
        entity.setExemplar(exemplar);
        entity.setLeitor(usuario);

        var domain = EmprestimoMapper.toDomain(entity);

        assertEquals(3L, domain.getLeitorId());
    }
}
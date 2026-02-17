package br.com.biblioteca.anandamoyi.infra.persistence.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.Emprestimo;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;

public class EmprestimoMapper {

    private EmprestimoMapper() {}

    // DOMAIN → ENTITY
    public static EmprestimoEntity toEntity(Emprestimo domain) {

        EmprestimoEntity entity = new EmprestimoEntity();

        entity.setId(domain.getId());

        // Exemplar
        ExemplarLivroEntity exemplar = new ExemplarLivroEntity();
        exemplar.setId(domain.getExemplarId());
        entity.setExemplar(exemplar);

        // Leitor
        UsuarioEntity leitor = new UsuarioEntity();
        leitor.setId(domain.getLeitorId());
        entity.setLeitor(leitor);

        entity.setDataEmprestimo(domain.getDataEmprestimo());
        entity.setDataPrevistaDevolucao(domain.getDataPrevistaDevolucao());
        entity.setDataDevolucao(domain.getDataDevolucao());
        entity.setDevolvido(domain.isDevolvido());
        entity.setMulta(domain.getMulta());

        return entity;
    }

    // ENTITY → DOMAIN
    public static Emprestimo toDomain(EmprestimoEntity entity) {

        return new Emprestimo(
                entity.getId(),
                entity.getExemplar().getId(),
                entity.getLeitor().getId(),
                entity.getDataEmprestimo(),
                entity.getDataPrevistaDevolucao(),
                entity.getDataDevolucao(),
                entity.isDevolvido(),
                entity.getMulta()
        );
    }
}

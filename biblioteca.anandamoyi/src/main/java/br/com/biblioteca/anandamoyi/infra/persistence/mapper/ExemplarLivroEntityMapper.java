package br.com.biblioteca.anandamoyi.infra.persistence.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.EdicaoLivroEntity;

public class ExemplarLivroEntityMapper {

    private ExemplarLivroEntityMapper() {}

    // =========================
    //  Entity → Domain (BLOQUEADO)
    // =========================
    public static ExemplarLivro toDomain(ExemplarLivroEntity entity) {
        throw new UnsupportedOperationException(
                "ExemplarLivro deve ser reconstruído via LivroEntityMapper"
        );
    }

    // =========================
    // Domain → Entity (PERSISTÊNCIA)
    // =========================
    public static ExemplarLivroEntity toEntity(ExemplarLivro exemplar) {

        if (exemplar == null) return null;

        ExemplarLivroEntity entity = new ExemplarLivroEntity();

        entity.setId(exemplar.getId());
        entity.setDisponivel(exemplar.isDisponivel());
        entity.setDataEmprestimo(exemplar.getDataEmprestimo());
        entity.setDataDevolucaoPrevista(exemplar.getDataDevolucaoPrevista());

        // Referência (FK), não agregação
        EdicaoLivroEntity edicaoEntity = new EdicaoLivroEntity();
        edicaoEntity.setId(exemplar.getEdicao().getId());

        entity.setEdicao(edicaoEntity);

        return entity;
    }
}

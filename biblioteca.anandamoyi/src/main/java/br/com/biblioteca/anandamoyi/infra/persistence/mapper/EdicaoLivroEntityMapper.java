package br.com.biblioteca.anandamoyi.infra.persistence.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.EdicaoLivro;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.EdicaoLivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.LivroEntity;

public class EdicaoLivroEntityMapper {

    private EdicaoLivroEntityMapper() {}

    // =========================
    // Domain → Entity (PERSISTÊNCIA)
    // =========================
    public static EdicaoLivroEntity toEntity(EdicaoLivro edicao) {

        if (edicao == null) return null;

        EdicaoLivroEntity entity = new EdicaoLivroEntity();

        entity.setId(edicao.getId());
        entity.setIsbn(edicao.getIsbn());
        entity.setEdicao(edicao.getEdicao());

        // 🔹 REFERÊNCIA, NÃO AGREGAÇÃO
        LivroEntity livroEntity = new LivroEntity();
        livroEntity.setId(edicao.getLivro().getId());
        entity.setLivro(livroEntity);

        return entity;
    }

    // =========================
    // BLOQUEIO ARQUITETURAL
    // =========================
    public static EdicaoLivro toDomain(EdicaoLivroEntity entity) {
        throw new UnsupportedOperationException(
                "EdicaoLivro deve ser reconstruída via LivroEntityMapper"
        );
    }
}

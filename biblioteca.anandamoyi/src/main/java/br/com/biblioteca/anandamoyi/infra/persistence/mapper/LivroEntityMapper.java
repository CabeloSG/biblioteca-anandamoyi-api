package br.com.biblioteca.anandamoyi.infra.persistence.mapper;

import br.com.biblioteca.anandamoyi.domain.entity.*;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.*;

public final class LivroEntityMapper {

    private LivroEntityMapper() {}

    // =========================
    // DOMAIN → ENTITY
    // =========================
    public static LivroEntity toEntity(Livro livro) {

        if (livro == null) return null;

        LivroEntity entity = new LivroEntity();
        entity.setId(livro.getId());
        entity.setTitulo(livro.getTitulo());
        entity.setAutor(livro.getAutor());
        entity.setCodigoBN(livro.getCodigoBN());

        livro.getEdicoes().forEach(edicao -> {
            EdicaoLivroEntity edicaoEntity = toEntity(edicao, entity);
            entity.getEdicoes().add(edicaoEntity);
        });

        return entity;
    }

    private static EdicaoLivroEntity toEntity(
            EdicaoLivro edicao,
            LivroEntity livroEntity
    ) {
        EdicaoLivroEntity entity = new EdicaoLivroEntity();
        entity.setId(edicao.getId());
        entity.setIsbn(edicao.getIsbn());
        entity.setEdicao(edicao.getEdicao());
        entity.setLivro(livroEntity);

        edicao.getExemplares().forEach(exemplar -> {
            ExemplarLivroEntity exemplarEntity =
                    toEntity(exemplar, entity);
            entity.getExemplares().add(exemplarEntity);
        });

        return entity;
    }

    private static ExemplarLivroEntity toEntity(
            ExemplarLivro exemplar,
            EdicaoLivroEntity edicaoEntity
    ) {
        ExemplarLivroEntity entity = new ExemplarLivroEntity();
        entity.setId(exemplar.getId());
        entity.setDisponivel(exemplar.isDisponivel());
        entity.setDataEmprestimo(exemplar.getDataEmprestimo());
        entity.setDataDevolucaoPrevista(
                exemplar.getDataDevolucaoPrevista()
        );
        entity.setEdicao(edicaoEntity);

        return entity;
    }

    // =========================
    // ENTITY → DOMAIN (SIMPLES)
    // =========================
    public static Livro toDomainSimples(LivroEntity entity) {

        if (entity == null) return null;

        return new Livro(
                entity.getId(),
                entity.getTitulo(),
                entity.getAutor(),
                entity.getCodigoBN()
        );
    }

    // =========================
    // ENTITY → DOMAIN (COMPLETO)
    // =========================
    public static Livro toDomain(LivroEntity entity) {

        if (entity == null) return null;

        Livro livro = new Livro(
                entity.getId(),
                entity.getTitulo(),
                entity.getAutor(),
                entity.getCodigoBN()
        );

        entity.getEdicoes().forEach(edicaoEntity -> {

            EdicaoLivro edicao = new EdicaoLivro(
                    edicaoEntity.getIsbn(),
                    edicaoEntity.getEdicao(),
                    livro
            );

            // ID DA EDIÇÃO (ESSENCIAL)
            edicao.setId(edicaoEntity.getId());

            edicaoEntity.getExemplares().forEach(exemplarEntity -> {

                ExemplarLivro exemplar = new ExemplarLivro(
                        exemplarEntity.getId(),                 // ID DO EXEMPLAR
                        exemplarEntity.isDisponivel(),
                        exemplarEntity.getDataEmprestimo(),
                        exemplarEntity.getDataDevolucaoPrevista(),
                        edicao
                );

                edicao.adicionarExemplar(exemplar);
            });

            livro.adicionarEdicao(edicao);
        });

        return livro;
    }
}

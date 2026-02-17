package br.com.biblioteca.anandamoyi.infra.persistence.repository;

import br.com.biblioteca.anandamoyi.infra.persistence.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroJpaRepository extends JpaRepository<LivroEntity, Long> {

    @Query("""
        select distinct l from LivroEntity l
        left join fetch l.edicoes e
        left join fetch e.exemplares
        where l.id = :id
    """)
    Optional<LivroEntity> buscarPorIdComEdicoesEExemplares( @Param("id") Long id);

    @Query("""
        SELECT l FROM LivroEntity l
        JOIN FETCH l.edicoes e
        JOIN FETCH e.exemplares
        WHERE e.id = :edicaoId
    """)
    Optional<LivroEntity> buscarPorEdicaoId(@Param("edicaoId") Long edicaoId);

        @Query("""
        SELECT DISTINCT l FROM LivroEntity l
        JOIN FETCH l.edicoes e
        JOIN FETCH e.exemplares ex
        WHERE ex.id = :exemplarId
    """)
        Optional<LivroEntity> buscarPorExemplarId(@Param("exemplarId") Long exemplarId);

}

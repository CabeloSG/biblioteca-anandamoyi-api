package br.com.biblioteca.anandamoyi.infra.persistence.repository;

import br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExemplarLivroJpaRepository
        extends JpaRepository<ExemplarLivroEntity, Long> {

    Optional<ExemplarLivroEntity>
    findFirstByEdicaoIdAndDisponivelTrue(Long edicaoId);
}

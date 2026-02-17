package br.com.biblioteca.anandamoyi.infra.persistence.repository;

import br.com.biblioteca.anandamoyi.infra.persistence.entity.EdicaoLivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EdicaoLivroJpaRepository
        extends JpaRepository<EdicaoLivroEntity, Long> {

    Optional<EdicaoLivroEntity> findByIsbn(String isbn);
}

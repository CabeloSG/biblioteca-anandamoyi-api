package br.com.biblioteca.anandamoyi.infra.persistence;

import br.com.biblioteca.anandamoyi.domain.entity.EdicaoLivro;
import br.com.biblioteca.anandamoyi.domain.repository.EdicaoLivroRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.mapper.EdicaoLivroEntityMapper;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.EdicaoLivroJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class EdicaoLivroRepositoryImpl implements EdicaoLivroRepository {

    private final EdicaoLivroJpaRepository jpaRepository;

    public EdicaoLivroRepositoryImpl(EdicaoLivroJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<EdicaoLivro> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(EdicaoLivroEntityMapper::toDomain);
    }

    @Override
    public Optional<EdicaoLivro> buscarPorIsbn(String isbn) {
        return jpaRepository.findByIsbn(isbn)
                .map(EdicaoLivroEntityMapper::toDomain);
    }
}

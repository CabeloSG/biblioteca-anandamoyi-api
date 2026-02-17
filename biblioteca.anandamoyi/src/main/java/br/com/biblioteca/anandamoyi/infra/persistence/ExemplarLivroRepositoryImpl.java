package br.com.biblioteca.anandamoyi.infra.persistence;

import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;
import br.com.biblioteca.anandamoyi.domain.repository.ExemplarLivroRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.mapper.ExemplarLivroEntityMapper;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.ExemplarLivroJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ExemplarLivroRepositoryImpl implements ExemplarLivroRepository {

    private final ExemplarLivroJpaRepository jpaRepository;

    public ExemplarLivroRepositoryImpl(
            ExemplarLivroJpaRepository jpaRepository
    ) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * Usado APENAS para atualizar estado (emprestar/devolver)
     */
    @Override
    public ExemplarLivro salvar(ExemplarLivro exemplar) {
        var entity = ExemplarLivroEntityMapper.toEntity(exemplar);
        jpaRepository.save(entity);
        return exemplar;
    }

    @Override
    public Optional<ExemplarLivro> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(ExemplarLivroEntityMapper::toDomain);
    }

    @Override
    public Optional<ExemplarLivro> buscarDisponivelPorEdicao(Long edicaoId) {
        return jpaRepository
                .findFirstByEdicaoIdAndDisponivelTrue(edicaoId)
                .map(ExemplarLivroEntityMapper::toDomain);
    }
}

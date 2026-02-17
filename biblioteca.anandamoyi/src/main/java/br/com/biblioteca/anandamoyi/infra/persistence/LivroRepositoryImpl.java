package br.com.biblioteca.anandamoyi.infra.persistence;

import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.LivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.mapper.LivroEntityMapper;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.LivroJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LivroRepositoryImpl implements LivroRepository {

    private final LivroJpaRepository livroJpaRepository;

    public LivroRepositoryImpl(LivroJpaRepository livroJpaRepository) {
        this.livroJpaRepository = livroJpaRepository;
    }

    @Override
    public Livro salvar(Livro livro) {
        LivroEntity entity = LivroEntityMapper.toEntity(livro);
        LivroEntity salvo = livroJpaRepository.save(entity);
        return LivroEntityMapper.toDomain(salvo);
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) {
        return livroJpaRepository.buscarPorIdComEdicoesEExemplares(id)
                .map(LivroEntityMapper::toDomain);
    }

    @Override
    public List<Livro> listarTodos() {
        return livroJpaRepository.findAll()
                .stream()
                .map(LivroEntityMapper::toDomainSimples)
                .toList();
    }

    @Override
    public Optional<Livro> buscarPorEdicaoId(Long edicaoId) {
        return livroJpaRepository.buscarPorEdicaoId(edicaoId)
                .map(LivroEntityMapper::toDomain);
    }

    @Override
    public Optional<Livro> buscarPorExemplarId(Long exemplarId) {
        return livroJpaRepository.buscarPorExemplarId(exemplarId)
                .map(LivroEntityMapper::toDomain);
    }

    @Override
    public boolean existePorId(Long id) {
        return livroJpaRepository.existsById(id);
    }

    @Override
    public void remover(Long id) {
        livroJpaRepository.deleteById(id);
    }
}

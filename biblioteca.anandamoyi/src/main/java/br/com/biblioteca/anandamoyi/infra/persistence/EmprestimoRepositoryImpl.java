package br.com.biblioteca.anandamoyi.infra.persistence;

import br.com.biblioteca.anandamoyi.domain.entity.Emprestimo;
import br.com.biblioteca.anandamoyi.domain.repository.EmprestimoRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.mapper.EmprestimoMapper;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.EmprestimoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class EmprestimoRepositoryImpl implements EmprestimoRepository {

    private final EmprestimoJpaRepository jpaRepository;

    public EmprestimoRepositoryImpl(EmprestimoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Emprestimo salvar(Emprestimo emprestimo) {
        EmprestimoEntity entity = EmprestimoMapper.toEntity(emprestimo);
        return EmprestimoMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public Optional<Emprestimo> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(EmprestimoMapper::toDomain);
    }

    @Override
    public List<Emprestimo> listarAtivos() {
        return jpaRepository.findByDevolvidoFalse()
                .stream()
                .map(EmprestimoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Emprestimo> listarPorExemplar(Long exemplarId) {
        return jpaRepository.findByExemplarId_Id(exemplarId)
                .stream()
                .map(EmprestimoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Emprestimo> listarAtivosPorLeitor(Long leitorId) {
        return jpaRepository.findByLeitorId_IdAndDevolvidoFalse(leitorId)
                .stream()
                .map(EmprestimoMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existeEmprestimoAtivoComAtraso(Long leitorId) {

        return jpaRepository
                .existsByLeitorId_IdAndDevolvidoFalseAndDataPrevistaDevolucaoBefore(
                        leitorId,
                        java.time.LocalDate.now()
                );
    }


}

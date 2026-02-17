package br.com.biblioteca.anandamoyi.infra.persistence.repository;

import br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface EmprestimoJpaRepository
        extends JpaRepository<EmprestimoEntity, Long> {

    List<EmprestimoEntity> findByDevolvidoFalse();

    List<EmprestimoEntity> findByExemplarId_Id(Long exemplarId);

    List<EmprestimoEntity> findByLeitorId_IdAndDevolvidoFalse(Long leitorId);

    Page<EmprestimoEntity> findByLeitorId_Id(Long leitorId, Pageable pageable);

    List<EmprestimoEntity> findByLeitorId_IdAndMultaGreaterThan(Long leitorId, double multa);

    boolean existsByLeitorId_IdAndDevolvidoFalseAndDataPrevistaDevolucaoBefore(
            Long leitorId,
            LocalDate data
    );

    Page<EmprestimoEntity> findAll(Pageable pageable);

    List<EmprestimoEntity> findByLeitorIdAndMultaGreaterThan(Long leitorId, double multa);

    Page<EmprestimoEntity> findByDevolvidoFalse(Pageable pageable);

    Page<EmprestimoEntity> findByDevolvidoTrue(Pageable pageable);

    @Query("""
       SELECT e FROM EmprestimoEntity e
       WHERE e.devolvido = false
       AND e.dataPrevistaDevolucao < CURRENT_DATE
    """)
    Page<EmprestimoEntity> findAtrasados(Pageable pageable);

}

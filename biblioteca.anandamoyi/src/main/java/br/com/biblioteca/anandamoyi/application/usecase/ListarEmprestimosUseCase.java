package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.EmprestimoResponseDTO;
import br.com.biblioteca.anandamoyi.domain.enums.StatusFiltroEmprestimo;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.EmprestimoJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.com.biblioteca.anandamoyi.domain.enums.StatusFiltroEmprestimo.*;

@Service
public class ListarEmprestimosUseCase {

    private final EmprestimoJpaRepository emprestimoRepository;

    public ListarEmprestimosUseCase(EmprestimoJpaRepository emprestimoRepository) {
        this.emprestimoRepository = emprestimoRepository;
    }

    public Page<EmprestimoResponseDTO> listar(
            Long leitorId,
            StatusFiltroEmprestimo status,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<EmprestimoEntity> resultado;

        if (leitorId != null) {
            resultado = emprestimoRepository.findByLeitorId_Id(leitorId, pageable);
        }
        else if (status != null) {

            switch (status) {

                case ATIVO ->
                        resultado = emprestimoRepository.findByDevolvidoFalse(pageable);

                case DEVOLVIDO ->
                        resultado = emprestimoRepository.findByDevolvidoTrue(pageable);

                case ATRASADO ->
                        resultado = emprestimoRepository.findAtrasados(pageable);

                default ->
                        resultado = emprestimoRepository.findAll(pageable);
            }

        } else {
            resultado = emprestimoRepository.findAll(pageable);
        }

        return resultado.map(EmprestimoResponseDTO::from);
    }
}

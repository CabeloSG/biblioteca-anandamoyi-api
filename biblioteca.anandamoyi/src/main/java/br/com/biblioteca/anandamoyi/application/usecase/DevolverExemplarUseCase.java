package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.ExemplarResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.Emprestimo;
import br.com.biblioteca.anandamoyi.domain.exception.RegraDeNegocioException;
import br.com.biblioteca.anandamoyi.domain.repository.EmprestimoRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.ExemplarLivroJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DevolverExemplarUseCase {

    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarLivroJpaRepository exemplarRepository;

    public DevolverExemplarUseCase(
            EmprestimoRepository emprestimoRepository,
            ExemplarLivroJpaRepository exemplarRepository
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
    }

    @Transactional
    public ExemplarResponseDTO executar(Long emprestimoId) {

        Emprestimo emprestimo = emprestimoRepository.buscarPorId(emprestimoId)
                .orElseThrow(() -> new RegraDeNegocioException("Empréstimo não encontrado")
                );

        if (emprestimo.isDevolvido()) {
            throw new RegraDeNegocioException("Empréstimo já devolvido");
        }

        // Registrar devolução (calcula multa automaticamente)
        emprestimo.registrarDevolucao(LocalDate.now());

        // Liberar exemplar
        ExemplarLivroEntity exemplar = exemplarRepository
                .findById(emprestimo.getExemplarId())
                .orElseThrow(() ->
                        new RegraDeNegocioException("Exemplar não encontrado")
                );

        exemplar.devolver();

        // Persistir alterações
        emprestimoRepository.salvar(emprestimo);

        return ExemplarResponseDTO.from(exemplar);
    }
}

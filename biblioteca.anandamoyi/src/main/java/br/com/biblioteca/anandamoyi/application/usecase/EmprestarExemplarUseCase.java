package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.ExemplarResponseDTO;
import br.com.biblioteca.anandamoyi.domain.exception.NenhumExemplarDisponivelException;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.ExemplarLivroEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.EmprestimoJpaRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.ExemplarLivroJpaRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class EmprestarExemplarUseCase {

    private final ExemplarLivroJpaRepository exemplarRepository;
    private final UsuarioJpaRepository usuarioRepository;
    private final EmprestimoJpaRepository emprestimoRepository;

    public EmprestarExemplarUseCase(
            ExemplarLivroJpaRepository exemplarRepository,
            UsuarioJpaRepository usuarioRepository,
            EmprestimoJpaRepository emprestimoRepository
    ) {
        this.exemplarRepository = exemplarRepository;
        this.usuarioRepository = usuarioRepository;
        this.emprestimoRepository = emprestimoRepository;
    }

    @Transactional
    public ExemplarResponseDTO executar(Long edicaoId, Long leitorId) {

        UsuarioEntity leitor = usuarioRepository.findById(leitorId)
                .orElseThrow(() -> new IllegalArgumentException("Leitor não encontrado"));

        leitor.validarPodeEmprestar();

        ExemplarLivroEntity exemplar = exemplarRepository
                .findFirstByEdicaoIdAndDisponivelTrue(edicaoId)
                .orElseThrow(() ->
                        new NenhumExemplarDisponivelException(edicaoId)
                );

        if (!exemplar.getEdicao().getLivro().isAtivo()) {
            throw new IllegalStateException("Livro está inativo");
        }

        LocalDate hoje = LocalDate.now();
        LocalDate devolucaoPrevista = hoje.plusDays(7);

        exemplar.emprestar(hoje, devolucaoPrevista);

        EmprestimoEntity emprestimo = new EmprestimoEntity(
                exemplar,
                leitor,
                hoje,
                devolucaoPrevista
        );

        emprestimoRepository.save(emprestimo);

        return ExemplarResponseDTO.from(exemplar);
    }


}

package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.EmprestimoJpaRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagarMultaUseCase {

    private final EmprestimoJpaRepository emprestimoRepository;
    private final UsuarioJpaRepository usuarioRepository;

    public PagarMultaUseCase(
            EmprestimoJpaRepository emprestimoRepository,
            UsuarioJpaRepository usuarioRepository
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void executar(Long emprestimoId) {

        EmprestimoEntity emprestimo = emprestimoRepository.findById(emprestimoId)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));

        emprestimo.quitarMulta(); // vamos criar esse método

        UsuarioEntity leitor = emprestimo.getLeitor();

        List<EmprestimoEntity> multasPendentes =
                emprestimoRepository.findByLeitorIdAndMultaGreaterThan(
                        leitor.getId(), 0.0
                );

        if (multasPendentes.isEmpty()) {
            leitor.desbloquear();
        }
    }
}

package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class ExcluirLivroUseCase {

    private final LivroRepository livroRepository;

    public ExcluirLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public void executar(Long id) {

        if (!livroRepository.existePorId(id)) {
            throw new IllegalArgumentException("Livro não encontrado");
        }

        livroRepository.remover(id);
    }
}

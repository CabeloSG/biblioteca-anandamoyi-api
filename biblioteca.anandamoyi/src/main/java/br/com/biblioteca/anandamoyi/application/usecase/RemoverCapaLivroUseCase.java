package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.LivroResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemoverCapaLivroUseCase {

    private final LivroRepository livroRepository;

    public RemoverCapaLivroUseCase(
            LivroRepository livroRepository
    ) {
        this.livroRepository = livroRepository;
    }

    @Transactional
    public LivroResponseDTO executar(Long id) {

        Livro livro = livroRepository.buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Livro não encontrado"
                        )
                );

        livro.setImagemUrl(null);

        Livro livroAtualizado =
                livroRepository.salvar(livro);

        return new LivroResponseDTO(
                livroAtualizado.getId(),
                livroAtualizado.getTitulo(),
                livroAtualizado.getAutor(),
                livroAtualizado.getCodigoBN(),
                livroAtualizado.getIsbn(),
                livroAtualizado.getEdicao(),
                livroAtualizado.getQuantidadeExemplares(),
                livroAtualizado.getImagemUrl()
        );
    }
}
package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.CriarLivroRequest;
import br.com.biblioteca.anandamoyi.application.dto.LivroResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.EdicaoLivro;
import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CriarLivroUseCase {

    private final LivroRepository livroRepository;

    public CriarLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Transactional
    public LivroResponseDTO executar(CriarLivroRequest request) {

        // ======================
        // Validações
        // ======================
        if (request.quantidadeExemplares() <= 0) {
            throw new IllegalArgumentException("Quantidade de exemplares inválida");
        }

        if (request.isbn() == null && request.codigoBN() == null) {
            throw new IllegalArgumentException("ISBN ou Código BN deve ser informado");
        }

        if (request.edicao() == null || request.edicao().isBlank()) {
            throw new IllegalArgumentException("Edição é obrigatória");
        }

        if (request.isbn() == null || request.isbn().isBlank()) {
            throw new IllegalArgumentException("ISBN é obrigatório");
        }

        // ======================
        // Cria LIVRO (Aggregate Root)
        // ======================
        Livro livro = new Livro(
                null,
                request.titulo(),
                request.autor(),
                request.codigoBN()
        );

        // ======================
        // Cria EDIÇÃO
        // ======================
        EdicaoLivro edicao = new EdicaoLivro(
                request.isbn(),
                request.edicao(),
                livro
        );

        livro.adicionarEdicao(edicao);

        // ======================
        // Cria EXEMPLARES
        // ======================
        for (int i = 0; i < request.quantidadeExemplares(); i++) {
            ExemplarLivro exemplar = new ExemplarLivro(edicao);
            edicao.adicionarExemplar(exemplar);
        }

        // ======================
        // Persistência
        // ======================
        Livro livroSalvo = livroRepository.salvar(livro);

        // ======================
        // Retorno
        // ======================
        return new LivroResponseDTO(
                livroSalvo.getId(),
                livroSalvo.getTitulo(),
                livroSalvo.getAutor(),
                livroSalvo.getCodigoBN()
        );
    }
}

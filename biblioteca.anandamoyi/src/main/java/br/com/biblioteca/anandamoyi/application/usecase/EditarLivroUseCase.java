package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.EditarLivroRequest;
import br.com.biblioteca.anandamoyi.application.dto.LivroResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class EditarLivroUseCase {

    private final LivroRepository livroRepository;

    public EditarLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroResponseDTO executar(Long id, EditarLivroRequest request) {

        Livro livro = livroRepository.buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Livro não encontrado"));

        livro.editar(

                request.titulo(),
                request.autor(),
                request.codigoBN(),
                request.isbn(),
                request.edicao()

        );

        Livro salvo = livroRepository.salvar(livro);

        return new LivroResponseDTO(
                salvo.getId(),
                salvo.getTitulo(),
                salvo.getAutor(),
                salvo.getCodigoBN()
        );
    }
}

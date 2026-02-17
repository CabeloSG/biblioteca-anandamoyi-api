package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.LivroResponseDTO;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarLivrosUseCase {

    private final LivroRepository livroRepository;

    public ListarLivrosUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<LivroResponseDTO> executar() {
        return livroRepository.listarTodos()
                .stream()
                .map(livro -> new LivroResponseDTO(
                        livro.getId(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        livro.getCodigoBN()
                ))
                .toList();
    }
}

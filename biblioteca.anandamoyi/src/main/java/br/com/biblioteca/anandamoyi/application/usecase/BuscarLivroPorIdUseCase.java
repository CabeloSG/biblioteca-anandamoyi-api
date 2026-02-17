package br.com.biblioteca.anandamoyi.application.usecase;

import br.com.biblioteca.anandamoyi.application.dto.LivroDetalhadoResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;
import br.com.biblioteca.anandamoyi.domain.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class BuscarLivroPorIdUseCase {

    private final LivroRepository repository;

    public BuscarLivroPorIdUseCase(LivroRepository repository) {
        this.repository = repository;
    }

    public LivroDetalhadoResponseDTO executar(Long id) {
        Livro livro = repository.buscarPorId(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Livro não encontrado"));

        return LivroDetalhadoResponseDTO.from(livro);
    }
}

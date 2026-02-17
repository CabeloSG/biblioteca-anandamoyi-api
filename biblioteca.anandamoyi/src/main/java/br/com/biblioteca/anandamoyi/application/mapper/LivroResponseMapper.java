package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.application.dto.LivroResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;

public class LivroResponseMapper {

    private LivroResponseMapper() {}

    public static LivroResponseDTO from(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigoBN()
        );
    }
}

package br.com.biblioteca.anandamoyi.application.mapper;

import br.com.biblioteca.anandamoyi.application.dto.LivroResponseDTO;
import br.com.biblioteca.anandamoyi.domain.entity.Livro;

public class LivroMapper {

    private LivroMapper() {
        // impede instanciação
    }

    public static LivroResponseDTO toResponse(Livro livro) {
        return new LivroResponseDTO(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getCodigoBN()
        );
    }
}
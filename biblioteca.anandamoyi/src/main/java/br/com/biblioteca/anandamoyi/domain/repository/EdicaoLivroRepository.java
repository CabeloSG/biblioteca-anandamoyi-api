package br.com.biblioteca.anandamoyi.domain.repository;

import br.com.biblioteca.anandamoyi.domain.entity.EdicaoLivro;

import java.util.Optional;

public interface EdicaoLivroRepository {

    Optional<EdicaoLivro> buscarPorId(Long id);

    Optional<EdicaoLivro> buscarPorIsbn(String isbn);
}

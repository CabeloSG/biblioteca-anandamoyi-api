package br.com.biblioteca.anandamoyi.domain.repository;

import br.com.biblioteca.anandamoyi.domain.entity.Livro;

import java.util.List;
import java.util.Optional;

public interface LivroRepository {

    Livro salvar(Livro livro);

    Optional<Livro> buscarPorId(Long id);

    List<Livro> listarTodos();

    Optional<Livro> buscarPorEdicaoId(Long edicaoId);

    Optional<Livro> buscarPorExemplarId(Long exemplarId);

    boolean existePorId(Long id);

    void remover(Long id);
}

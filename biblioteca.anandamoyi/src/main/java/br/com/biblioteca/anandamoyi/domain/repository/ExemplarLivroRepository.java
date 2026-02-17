package br.com.biblioteca.anandamoyi.domain.repository;

import br.com.biblioteca.anandamoyi.domain.entity.ExemplarLivro;

import java.util.Optional;

public interface ExemplarLivroRepository {

    ExemplarLivro salvar(ExemplarLivro exemplar);

    Optional<ExemplarLivro> buscarPorId(Long id);

    Optional<ExemplarLivro> buscarDisponivelPorEdicao(Long edicaoId);
}

package br.com.biblioteca.anandamoyi.domain.repository;

import br.com.biblioteca.anandamoyi.domain.entity.Emprestimo;

import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository {

    Emprestimo salvar(Emprestimo emprestimo);

    Optional<Emprestimo> buscarPorId(Long id);

    List<Emprestimo> listarAtivos();

    List<Emprestimo> listarPorExemplar(Long exemplarId);

    List<Emprestimo> listarAtivosPorLeitor(Long leitorId);

    boolean existeEmprestimoAtivoComAtraso(Long leitorId);


}

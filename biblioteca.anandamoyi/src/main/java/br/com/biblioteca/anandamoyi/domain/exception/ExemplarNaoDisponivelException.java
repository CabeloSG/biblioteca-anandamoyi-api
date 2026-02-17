package br.com.biblioteca.anandamoyi.domain.exception;

public class ExemplarNaoDisponivelException extends RuntimeException {

    public ExemplarNaoDisponivelException(Long edicaoId) {
        super("Nenhum exemplar disponível para a edição id: " + edicaoId);
    }
}

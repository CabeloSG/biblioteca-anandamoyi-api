package br.com.biblioteca.anandamoyi.domain.exception;

public class ExemplarNaoEncontradoException extends RuntimeException {

    public ExemplarNaoEncontradoException(Long exemplarId) {
        super("Exemplar não encontrado");
    }
}

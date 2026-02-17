package br.com.biblioteca.anandamoyi.domain.exception;

public class ExemplarInDisponivelException extends RuntimeException {

    public ExemplarInDisponivelException(Long edicaoId) {
        super("Nenhum exemplar disponível para a edição " + edicaoId);
    }
}

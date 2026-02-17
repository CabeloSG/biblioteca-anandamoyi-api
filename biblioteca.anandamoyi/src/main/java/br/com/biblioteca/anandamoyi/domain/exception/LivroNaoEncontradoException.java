package br.com.biblioteca.anandamoyi.domain.exception;

public class LivroNaoEncontradoException extends RuntimeException {

    public LivroNaoEncontradoException(Long id) {
        super("Livro não encontrado com id: " + id);
    }
}

package br.com.biblioteca.anandamoyi.domain.exception;

public class LivroNaoEmprestadoException extends RuntimeException {

    public LivroNaoEmprestadoException() {
        super("Livro não está emprestado");
    }
}

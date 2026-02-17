package br.com.biblioteca.anandamoyi.domain.exception;

public class LivroJaEmprestadoException extends RuntimeException {

    public LivroJaEmprestadoException() {
        super("Livro já está emprestado");
    }
}

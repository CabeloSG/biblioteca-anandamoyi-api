package br.com.biblioteca.anandamoyi.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class EdicaoLivro {

    private Long id;
    private String isbn;
    private String edicao;
    private Livro livro;

    private final List<ExemplarLivro> exemplares = new ArrayList<>();

    public EdicaoLivro(String isbn, String edicao, Livro livro) {
        this.isbn = isbn;
        this.edicao = edicao;
        this.livro = livro;
    }

    // ======================
    // Regras de negócio
    // ======================

    public void adicionarExemplar(ExemplarLivro exemplar) {
        exemplares.add(exemplar);
    }

    // ======================
    // Getters / setters
    // ======================

    public Long getId() { return id; }
    public String getIsbn() { return isbn; }
    public String getEdicao() { return edicao; }
    public Livro getLivro() { return livro; }
    public List<ExemplarLivro> getExemplares() { return exemplares; }

    void setLivro(Livro livro) {
        this.livro = livro;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }
}

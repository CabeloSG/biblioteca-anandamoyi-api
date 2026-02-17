package br.com.biblioteca.anandamoyi.domain.entity;

import java.time.LocalDate;

public class ExemplarLivro {

    private Long id;
    private boolean disponivel = true;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private EdicaoLivro edicao;

    protected ExemplarLivro() {}

    public ExemplarLivro(EdicaoLivro edicao) {
        this.edicao = edicao;
    }

    public ExemplarLivro(Long id, boolean disponivel, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, EdicaoLivro edicao) {
        this.id = id;
        this.disponivel = disponivel;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.edicao = edicao;
    }

    // ======================
    // Regras de negócio
    // ======================

    public void emprestar(int prazoDias) {
        if (!disponivel) {
            throw new IllegalStateException("Exemplar indisponível");
        }
        this.disponivel = false;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucaoPrevista = LocalDate.now().plusDays(prazoDias);
    }

    public void devolver() {
        if (disponivel) {
            throw new IllegalStateException("Exemplar não está emprestado");
        }
        this.disponivel = true;
        this.dataEmprestimo = null;
        this.dataDevolucaoPrevista = null;
    }

    // ======================
    // Getters / setters
    // ======================

    public Long getId() { return id; }
    public boolean isDisponivel() { return disponivel; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public EdicaoLivro getEdicao() { return edicao; }

    void setEdicao(EdicaoLivro edicao) {
        this.edicao = edicao;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

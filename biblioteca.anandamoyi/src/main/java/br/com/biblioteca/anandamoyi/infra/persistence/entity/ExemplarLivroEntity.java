package br.com.biblioteca.anandamoyi.infra.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "exemplares")
public class ExemplarLivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean disponivel = true;

    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edicao_id", nullable = false)
    private EdicaoLivroEntity edicao;

    public ExemplarLivroEntity() {
        // JPA
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public EdicaoLivroEntity getEdicao() {
        return edicao;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public void setEdicao(EdicaoLivroEntity edicao) {
        this.edicao = edicao;
    }

    // ===== REGRAS DE DOMÍNIO =====

    public void emprestar(LocalDate dataEmprestimo, LocalDate dataPrevistaDevolucao) {

        if (!this.disponivel) {
            throw new IllegalStateException("Exemplar já está emprestado");
        }

        this.disponivel = false;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataPrevistaDevolucao;
    }

    public void devolver() {

        if (this.disponivel) {
            throw new IllegalStateException("Exemplar já está disponível");
        }

        this.disponivel = true;
        this.dataEmprestimo = null;
        this.dataDevolucaoPrevista = null;
    }
}

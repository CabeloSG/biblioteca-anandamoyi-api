package br.com.biblioteca.anandamoyi.infra.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "emprestimos")
public class EmprestimoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    private boolean devolvido;

    private Double multa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exemplar_id", nullable = false)
    private ExemplarLivroEntity exemplar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leitor_id", nullable = false)
    private UsuarioEntity leitor;

    public EmprestimoEntity() {
        // JPA
    }

    public EmprestimoEntity(
            ExemplarLivroEntity exemplar,
            UsuarioEntity leitor,
            LocalDate dataEmprestimo,
            LocalDate dataPrevistaDevolucao
    ) {
        this.exemplar = exemplar;
        this.leitor = leitor;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.devolvido = false;
        this.multa = 0.0;
    }

    public void registrarDevolucao(LocalDate dataDevolucao) {

        if (this.devolvido) {
            throw new IllegalStateException("Empréstimo já foi devolvido");
        }

        this.dataDevolucao = dataDevolucao;
        this.devolvido = true;

        if (dataDevolucao.isAfter(dataPrevistaDevolucao)) {

            long diasAtraso = ChronoUnit.DAYS.between(
                    dataPrevistaDevolucao,
                    dataDevolucao
            );

            this.multa = diasAtraso * 2.0; // R$2 por dia
        }
    }

    public void quitarMulta() {
        this.multa = 0.0;
    }


    // ===== Getters =====

    public Long getId() {
        return id;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public Double getMulta() {
        return multa;
    }

    public ExemplarLivroEntity getExemplar() {
        return exemplar;
    }

    public UsuarioEntity getLeitor() {
        return leitor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataPrevistaDevolucao(LocalDate dataPrevistaDevolucao) {
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public void setExemplar(ExemplarLivroEntity exemplar) {
        this.exemplar = exemplar;
    }

    public void setLeitor(UsuarioEntity leitor) {
        this.leitor = leitor;
    }
}

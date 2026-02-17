package br.com.biblioteca.anandamoyi.domain.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    private Long id;
    private Long exemplarId;
    private Long leitorId;

    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;

    private boolean devolvido;
    private Double multa;

    // 🔹 Construtor para criar novo empréstimo
    public Emprestimo(Long exemplarId, Long leitorId) {
        this.exemplarId = exemplarId;
        this.leitorId = leitorId;
        this.dataEmprestimo = LocalDate.now();
        this.dataPrevistaDevolucao = LocalDate.now().plusDays(7);
        this.devolvido = false;
        this.multa = 0.0;
    }

    // 🔹 Construtor completo (usado pelo Mapper)
    public Emprestimo(
            Long id,
            Long exemplarId,
            Long leitorId,
            LocalDate dataEmprestimo,
            LocalDate dataPrevistaDevolucao,
            LocalDate dataDevolucao,
            boolean devolvido,
            Double multa
    ) {
        this.id = id;
        this.exemplarId = exemplarId;
        this.leitorId = leitorId;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
        this.multa = multa;
    }

    // 🔥 REGRA DE NEGÓCIO
    public void registrarDevolucao(LocalDate dataDevolucao) {

        if (this.devolvido) {
            throw new IllegalStateException("Empréstimo já devolvido");
        }

        this.dataDevolucao = dataDevolucao;
        this.devolvido = true;

        if (dataDevolucao.isAfter(dataPrevistaDevolucao)) {
            long diasAtraso = ChronoUnit.DAYS.between(
                    dataPrevistaDevolucao,
                    dataDevolucao
            );
            this.multa = diasAtraso * 2.0;
        }
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public Long getExemplarId() {
        return exemplarId;
    }

    public Long getLeitorId() {
        return leitorId;
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
}

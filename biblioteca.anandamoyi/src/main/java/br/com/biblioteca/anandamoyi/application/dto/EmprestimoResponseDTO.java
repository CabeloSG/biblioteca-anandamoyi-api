package br.com.biblioteca.anandamoyi.application.dto;

import br.com.biblioteca.anandamoyi.infra.persistence.entity.EmprestimoEntity;

import java.time.LocalDate;

public class EmprestimoResponseDTO {

    private Long id;
    private String leitorNome;
    private String livroTitulo;
    private LocalDate dataEmprestimo;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucao;
    private boolean devolvido;
    private double multa;

    public EmprestimoResponseDTO(
            Long id,
            String leitorNome,
            String livroTitulo,
            LocalDate dataEmprestimo,
            LocalDate dataPrevistaDevolucao,
            LocalDate dataDevolucao,
            boolean devolvido,
            double multa
    ) {
        this.id = id;
        this.leitorNome = leitorNome;
        this.livroTitulo = livroTitulo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataPrevistaDevolucao;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
        this.multa = multa;
    }

    public static EmprestimoResponseDTO from(EmprestimoEntity e) {
        return new EmprestimoResponseDTO(
                e.getId(),
                e.getLeitor().getNome(),
                e.getExemplar().getEdicao().getLivro().getTitulo(),
                e.getDataEmprestimo(),
                e.getDataPrevistaDevolucao(),
                e.getDataDevolucao(),
                e.isDevolvido(),
                e.getMulta()
        );
    }

    // getters
    public Long getId() { return id; }
    public String getLeitorNome() { return leitorNome; }
    public String getLivroTitulo() { return livroTitulo; }
    public LocalDate getDataEmprestimo() { return dataEmprestimo; }
    public LocalDate getDataPrevistaDevolucao() { return dataPrevistaDevolucao; }
    public LocalDate getDataDevolucao() { return dataDevolucao; }
    public boolean isDevolvido() { return devolvido; }
    public double getMulta() { return multa; }
}

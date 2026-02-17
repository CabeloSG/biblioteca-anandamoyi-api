package br.com.biblioteca.anandamoyi.domain.entity;

import br.com.biblioteca.anandamoyi.domain.exception.ExemplarInDisponivelException;

import java.util.ArrayList;
import java.util.List;

public class Livro {

    private Long id;
    private String titulo;
    private String autor;
    private String codigoBN;
    private String isbn;
    private Integer edicao;

    private final List<EdicaoLivro> edicoes = new ArrayList<>();

    public Livro(String titulo, String autor, String codigoBN) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigoBN = codigoBN;
    }

    public Livro(Long id, String titulo, String autor, String codigoBN) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.codigoBN = codigoBN;
    }

    // ======================
    // Regras de negócio
    // ======================

    public void adicionarEdicao(EdicaoLivro edicao) {
        edicoes.add(edicao);
    }

    // ======================
    // Getters
    // ======================

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCodigoBN() { return codigoBN; }
    public List<EdicaoLivro> getEdicoes() { return edicoes; }
    public Integer getEdicao() { return edicao; }
    public String getIsbn() { return isbn; }

    public ExemplarLivro emprestarExemplarDaEdicao(Long edicaoId) {

        EdicaoLivro edicao = this.edicoes.stream()
                .filter(e -> e.getId().equals(edicaoId))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Edição não encontrada")
                );

        ExemplarLivro exemplar = edicao.getExemplares().stream()
                .filter(ExemplarLivro::isDisponivel)
                .findFirst()
                .orElseThrow(() ->
                        new ExemplarInDisponivelException(edicaoId)
                );

        exemplar.emprestar(7);
        return exemplar;
    }

    public ExemplarLivro buscarExemplarPorId(Long exemplarId) {
        return this.edicoes.stream()
                .flatMap(edicao -> edicao.getExemplares().stream())
                .filter(exemplar -> exemplar.getId().equals(exemplarId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Exemplar não encontrado no livro")
                );
    }

    public void editar(
            String titulo,
            String autor,
            String codigoBN,
            String isbn,
            Integer edicao
    ) {
        this.titulo = titulo;
        this.autor = autor;
        this.codigoBN = codigoBN;
        this.isbn = isbn;
        this.edicao = edicao;
    }



}

package br.com.biblioteca.anandamoyi.infra.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "livros")
public class LivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String autor;

    @Column(unique = true)
    private String codigoBN;

    @Column(unique = true)
    private String isbn;

    private Integer edicao;

    private Integer quantidadeExemplares;

    @Column(nullable = false)
    private boolean ativo;

    @OneToMany(
            mappedBy = "livro",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<EdicaoLivroEntity> edicoes = new HashSet<>();

    public LivroEntity() {
        this.ativo = true;
    }

    // GETTERS E SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCodigoBN() {
        return codigoBN;
    }

    public void setCodigoBN(String codigoBN) {
        this.codigoBN = codigoBN;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }

    public Integer getQuantidadeExemplares() {
        return quantidadeExemplares;
    }

    public void setQuantidadeExemplares(Integer quantidadeExemplares) {
        this.quantidadeExemplares = quantidadeExemplares;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public Set<EdicaoLivroEntity> getEdicoes() {
        return edicoes;
    }
}
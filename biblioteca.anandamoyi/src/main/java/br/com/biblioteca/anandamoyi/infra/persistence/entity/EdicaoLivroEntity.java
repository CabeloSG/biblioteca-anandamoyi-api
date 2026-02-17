package br.com.biblioteca.anandamoyi.infra.persistence.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "edicoes_livro")
public class EdicaoLivroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String edicao;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private LivroEntity livro;

    @OneToMany(
            mappedBy = "edicao",
            cascade = CascadeType.PERSIST
    )
    private Set<ExemplarLivroEntity> exemplares = new HashSet<>();

    public EdicaoLivroEntity() {}

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getEdicao() { return edicao; }
    public void setEdicao(String edicao) { this.edicao = edicao; }

    public LivroEntity getLivro() { return livro; }
    public void setLivro(LivroEntity livro) { this.livro = livro; }

    public Set<ExemplarLivroEntity> getExemplares() { return exemplares; }
}


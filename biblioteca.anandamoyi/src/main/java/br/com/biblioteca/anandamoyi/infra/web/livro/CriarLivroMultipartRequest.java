package br.com.biblioteca.anandamoyi.infra.web.livro;

import org.springframework.web.multipart.MultipartFile;

public class CriarLivroMultipartRequest {

    private String titulo;
    private String autor;
    private String codigoBN;
    private String isbn;
    private String edicao;
    private Integer quantidadeExemplares;
    private MultipartFile arquivo;

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCodigoBN() {
        return codigoBN;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getEdicao() {
        return edicao;
    }

    public Integer getQuantidadeExemplares() {
        return quantidadeExemplares;
    }

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setCodigoBN(String codigoBN) {
        this.codigoBN = codigoBN;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public void setQuantidadeExemplares(Integer quantidadeExemplares) {
        this.quantidadeExemplares = quantidadeExemplares;
    }

    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }
}
package br.com.biblioteca.anandamoyi.infra.web.livro;

public class CriarLivroWebRequest {

    private String titulo;
    private String autor;
    private String codigoBN;
    private String isbn;
    private String edicao;
    private int quantidadeExemplares;

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getCodigoBN() { return codigoBN; }
    public String getIsbn() { return isbn; }
    public String getEdicao() { return edicao; }
    public int getQuantidadeExemplares() { return quantidadeExemplares; }
}

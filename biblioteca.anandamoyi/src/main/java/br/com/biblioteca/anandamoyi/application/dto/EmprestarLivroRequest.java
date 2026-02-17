package br.com.biblioteca.anandamoyi.application.dto;

public class EmprestarLivroRequest {

    private Long livroId;

    public EmprestarLivroRequest(Long livroId) {
        this.livroId = livroId;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }
}

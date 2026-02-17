package br.com.biblioteca.anandamoyi.application.dto;

public class EmprestarExemplarRequest {

    private Long edicaoId;

    public Long getEdicaoId() {
        return edicaoId;
    }

    public void setEdicaoId(Long edicaoId) {
        this.edicaoId = edicaoId;
    }
}

package br.com.biblioteca.anandamoyi.domain.usuario;

import br.com.biblioteca.anandamoyi.domain.enums.Role;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senhaHash;
    private Role role;
    private boolean ativo;

    public Usuario(
            Long id,
            String nome,
            String email,
            String senhaHash,
            Role role,
            boolean ativo
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaHash = senhaHash;
        this.role = role;
        this.ativo = ativo;
    }

    public static Usuario criar(String nome, String email, String senhaHash, Role role) {
        return new Usuario(
                null,
                nome,
                email,
                senhaHash,
                role,
                true
        );
    }



    public boolean isAtivo() {
        return ativo;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public boolean isLBibliotecario() {
        return role == Role.BIBLIOTECARIO;
    }

    public boolean isLeitor() {
        return role == Role.LEITOR;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public Role getRole() {
        return role;
    }

    public String getNome() {
        return nome;
    }

}

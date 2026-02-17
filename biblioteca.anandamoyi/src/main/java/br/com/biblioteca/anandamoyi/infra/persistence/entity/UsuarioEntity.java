package br.com.biblioteca.anandamoyi.infra.persistence.entity;


import br.com.biblioteca.anandamoyi.domain.enums.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private String senhaHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private boolean ativo;

    private boolean bloqueado;

    public UsuarioEntity() {
        //JPA
    }

    public UsuarioEntity(
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void bloquear() {
        this.bloqueado = true;
    }

    public void desbloquear() {
        this.bloqueado = false;
    }

    public void validarPodeEmprestar() {
        if (!this.ativo) {
            throw new IllegalStateException("Usuário inativo");
        }

        if (this.bloqueado) {
            throw new IllegalStateException("Usuário bloqueado por multa pendente");
        }
    }

}


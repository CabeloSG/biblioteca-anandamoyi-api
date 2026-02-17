package br.com.biblioteca.anandamoyi.infra.persistence.mapper;


import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;

public class UsuarioMapper {

    public static UsuarioEntity toEntity(Usuario usuario) {

        if (usuario == null) return null;

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNome(usuario.getNome());
        entity.setEmail(usuario.getEmail());
        entity.setSenhaHash(usuario.getSenhaHash());
        entity.setRole(usuario.getRole());
        entity.setAtivo(usuario.isAtivo());

        return entity;
    }

    public static Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;
        return new Usuario(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getSenhaHash(),
                entity.getRole(),
                entity.isAtivo()
        );
    }
}

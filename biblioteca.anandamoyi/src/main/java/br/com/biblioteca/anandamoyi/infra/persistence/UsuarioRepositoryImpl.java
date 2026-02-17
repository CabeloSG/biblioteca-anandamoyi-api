package br.com.biblioteca.anandamoyi.infra.persistence;

import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.mapper.UsuarioMapper;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.UsuarioJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryImpl(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntity entity = UsuarioMapper.toEntity(usuario);
        UsuarioEntity salvo = jpaRepository.save(entity);
        return UsuarioMapper.toDomain(salvo);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository
                .findByEmail(email)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository
                .findById(id)
                .map(UsuarioMapper::toDomain);
    }
}

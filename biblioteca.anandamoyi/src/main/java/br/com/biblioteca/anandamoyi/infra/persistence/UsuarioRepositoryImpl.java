package br.com.biblioteca.anandamoyi.infra.persistence;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.infra.persistence.entity.UsuarioEntity;
import br.com.biblioteca.anandamoyi.infra.persistence.mapper.UsuarioMapper;
import br.com.biblioteca.anandamoyi.infra.persistence.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository jpaRepository;

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.findByEmail(email).isPresent();
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioEntity entity = UsuarioMapper.toEntity(usuario);
        UsuarioEntity salvo = jpaRepository.save(entity);
        return UsuarioMapper.toDomain(salvo);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> findAll() {
        return jpaRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDomain)
                .toList();
    }

    @Override
    public List<Usuario> executar() {
        return executar();
    }

    @Override
    public void deletarTodos() {
        jpaRepository.deleteAll();
    }

}

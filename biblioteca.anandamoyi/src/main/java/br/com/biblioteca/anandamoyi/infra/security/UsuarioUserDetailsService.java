package br.com.biblioteca.anandamoyi.infra.security;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioUserDetailsService
        implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioUserDetailsService(
            UsuarioRepository usuarioRepository
    ) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {

        Usuario usuario = usuarioRepository.buscarPorEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuário não encontrado")
                );

        return new UserDetailsAdapter(usuario);
    }
}

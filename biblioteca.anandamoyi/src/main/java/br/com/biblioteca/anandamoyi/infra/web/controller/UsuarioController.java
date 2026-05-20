package br.com.biblioteca.anandamoyi.infra.web.controller;

import br.com.biblioteca.anandamoyi.application.usuario.CriarUsuarioCommand;
import br.com.biblioteca.anandamoyi.application.usuario.CriarUsuarioUseCase;
import br.com.biblioteca.anandamoyi.application.usuario.ListarUsuarioUseCase;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final ListarUsuarioUseCase listarUsuarioUseCase;

    // CRIAR USUÁRIO
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criar(
            @Valid @RequestBody CriarUsuarioCommand command
    ) {
        return criarUsuarioUseCase.executar(command);
    }

    // LISTAR USUÁRIOS
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Usuario> listar() {
        return listarUsuarioUseCase.executar();
    }
}
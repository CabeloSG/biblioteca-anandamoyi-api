package br.com.biblioteca.anandamoyi.infra.web.controller;

import br.com.biblioteca.anandamoyi.application.usuario.CriarUsuarioCommand;
import br.com.biblioteca.anandamoyi.application.usuario.CriarUsuarioRequest;
import br.com.biblioteca.anandamoyi.application.usuario.CriarUsuarioUseCase;
import br.com.biblioteca.anandamoyi.application.usuario.UsuarioResponseDTO;
import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(
            @RequestBody CriarUsuarioRequest request
    ) {

        CriarUsuarioCommand command = new CriarUsuarioCommand(
                request.nome(),
                request.email(),
                request.senha(),
                Role.valueOf(request.role()),
                request.ativo()
        );

        Usuario usuario = criarUsuarioUseCase.executar(command);

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.isAtivo()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}



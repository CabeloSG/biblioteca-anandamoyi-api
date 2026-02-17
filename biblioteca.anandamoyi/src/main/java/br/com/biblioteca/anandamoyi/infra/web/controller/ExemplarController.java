package br.com.biblioteca.anandamoyi.infra.web.controller;

import br.com.biblioteca.anandamoyi.application.dto.ExemplarResponseDTO;
import br.com.biblioteca.anandamoyi.application.usecase.EmprestarExemplarUseCase;
import br.com.biblioteca.anandamoyi.application.usecase.DevolverExemplarUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exemplares")
public class ExemplarController {

    private final EmprestarExemplarUseCase emprestarExemplarUseCase;
    private final DevolverExemplarUseCase devolverExemplarUseCase;

    public ExemplarController(
            EmprestarExemplarUseCase emprestarExemplarUseCase,
            DevolverExemplarUseCase devolverExemplarUseCase
    ) {
        this.emprestarExemplarUseCase = emprestarExemplarUseCase;
        this.devolverExemplarUseCase = devolverExemplarUseCase;
    }

    // 🔹 PUT /exemplares/{id}/emprestar
    @PutMapping("/{id}/emprestar")
    public ResponseEntity<ExemplarResponseDTO> emprestar(
            @PathVariable Long id,
            @RequestParam Long leitorId
    ) {
        ExemplarResponseDTO response =
                emprestarExemplarUseCase.executar(id, leitorId);
        return ResponseEntity.ok(response);
    }

    // PUT /exemplares/{id}/devolver
    @PutMapping("/{id}/devolver")
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    public ResponseEntity<ExemplarResponseDTO> devolver(@PathVariable Long id) {

        return ResponseEntity.ok(
                devolverExemplarUseCase.executar(id)
        );
    }

    // GET /exemplares/teste
    @GetMapping("/teste")
    public String teste() {
        return "ok";
    }
}

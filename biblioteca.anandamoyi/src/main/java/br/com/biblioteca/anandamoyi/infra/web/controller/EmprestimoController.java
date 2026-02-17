package br.com.biblioteca.anandamoyi.infra.web.controller;


import br.com.biblioteca.anandamoyi.application.dto.EmprestimoResponseDTO;
import br.com.biblioteca.anandamoyi.application.usecase.DevolverExemplarUseCase;
import br.com.biblioteca.anandamoyi.application.usecase.ListarEmprestimosUseCase;
import br.com.biblioteca.anandamoyi.domain.enums.StatusFiltroEmprestimo;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final DevolverExemplarUseCase devolverExemplarUseCase;
    private final ListarEmprestimosUseCase listarUseCase;

    public EmprestimoController(
            DevolverExemplarUseCase devolverExemplarUseCase,
            ListarEmprestimosUseCase listarUseCase
    ) {
        this.devolverExemplarUseCase = devolverExemplarUseCase;
        this.listarUseCase = listarUseCase;
    }


    @PutMapping("/emprestimos/{id}/devolver")
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    public ResponseEntity<Void> devolver(@PathVariable Long id) {

        devolverExemplarUseCase.executar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public Page<EmprestimoResponseDTO> listar(
            @RequestParam(required = false) Long leitorId,
            @RequestParam(required = false) StatusFiltroEmprestimo status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return listarUseCase.listar(leitorId, status, page, size);
    }



}

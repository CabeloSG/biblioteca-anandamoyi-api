package br.com.biblioteca.anandamoyi.infra.web.controller;

import br.com.biblioteca.anandamoyi.application.dto.*;
import br.com.biblioteca.anandamoyi.application.usecase.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import br.com.biblioteca.anandamoyi.infra.storage.UploadImagemService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final CriarLivroUseCase criarLivroUseCase;
    private final ListarLivrosUseCase listarLivrosUseCase;
    private final BuscarLivroPorIdUseCase buscarLivroPorIdUseCase;
    private final EmprestarExemplarUseCase emprestarExemplarUseCase;
    private final EditarLivroUseCase editarLivroUseCase;
    private final ExcluirLivroUseCase excluirLivroUseCase;
    private final UploadImagemService uploadImagemService;



    public LivroController(
            CriarLivroUseCase criarLivroUseCase,
            ListarLivrosUseCase listarLivrosUseCase,
            BuscarLivroPorIdUseCase buscarLivroPorIdUseCase,
            EmprestarExemplarUseCase emprestarExemplarUseCase,
            EditarLivroUseCase editarLivroUseCase,
            ExcluirLivroUseCase excluirLivroUseCase,
            UploadImagemService uploadImagemService
    ) {
        this.criarLivroUseCase = criarLivroUseCase;
        this.listarLivrosUseCase = listarLivrosUseCase;
        this.buscarLivroPorIdUseCase = buscarLivroPorIdUseCase;
        this.emprestarExemplarUseCase = emprestarExemplarUseCase;
        this.editarLivroUseCase = editarLivroUseCase;
        this.excluirLivroUseCase = excluirLivroUseCase;
        this.uploadImagemService = uploadImagemService;
    }


    // ADMIN | BIBLIOTECARIO | LEITOR
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO','LEITOR')")
    public ResponseEntity<List<LivroResponseDTO>> listar() {
        return ResponseEntity.ok(listarLivrosUseCase.executar());
    }

    // ADMIN | BIBLIOTECARIO | LEITOR
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO','LEITOR')")
    public ResponseEntity<LivroDetalhadoResponseDTO> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(buscarLivroPorIdUseCase.executar(id));
    }

    // ADMIN | BIBLIOTECARIO
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    public ResponseEntity<LivroResponseDTO> criar(
            @Valid @RequestBody CriarLivroRequest request
    ) {
        LivroResponseDTO response = criarLivroUseCase.executar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ADMIN | BIBLIOTECARIO
    @PostMapping("/edicoes/{edicaoId}/emprestar")
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    public ResponseEntity<ExemplarResponseDTO> emprestarExemplar(
            @PathVariable Long edicaoId,
            @RequestParam Long leitorId
    ) {
        return ResponseEntity.ok(
                emprestarExemplarUseCase.executar(edicaoId, leitorId)
        );
    }

    // ADMIN | BIBLIOTECARIO
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    public ResponseEntity<LivroResponseDTO> editar(
            @PathVariable Long id,
            @RequestBody EditarLivroRequest request
    ) {
        return ResponseEntity.ok(
                editarLivroUseCase.executar(id, request)
        );
    }

    // ADMIN | BIBLIOTECARIO
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirLivroUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(
            value = "/upload-capa",
            consumes = {"multipart/form-data"}
    )
    @PreAuthorize("hasAnyRole('ADMIN','BIBLIOTECARIO')")
    public ResponseEntity<String> uploadCapa(
            @RequestPart("arquivo") MultipartFile arquivo
    ) {

        String url = uploadImagemService.salvar(arquivo);

        return ResponseEntity.ok(url);
    }

    @GetMapping("/teste-arquivo")
    public ResponseEntity<String> testeArquivo() {

        java.io.File file = new java.io.File(
                "uploads/livros/d59a5303-bd86-485e-affa-6a605d2760a1.jpeg"
        );

        if (file.exists()) {
            return ResponseEntity.ok("EXISTE");
        }

        return ResponseEntity.ok("NAO EXISTE");
    }


}

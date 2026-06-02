package br.com.biblioteca.anandamoyi.infra.web.controller;

import br.com.biblioteca.anandamoyi.application.dto.*;
import br.com.biblioteca.anandamoyi.application.usecase.*;
import br.com.biblioteca.anandamoyi.infra.security.JwtFilter;
import br.com.biblioteca.anandamoyi.infra.security.JwtService;
import br.com.biblioteca.anandamoyi.infra.web.exception.GlobalExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = LivroController.class,
        excludeAutoConfiguration = {
                SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class
        }
)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtFilter jwtFilter;

    @MockBean
    JwtService jwtService;

    @MockBean
    private CriarLivroUseCase criarLivroUseCase;

    @MockBean
    private ListarLivrosUseCase listarLivrosUseCase;

    @MockBean
    private BuscarLivroPorIdUseCase buscarLivroPorIdUseCase;

    @MockBean
    private EmprestarExemplarUseCase emprestarExemplarUseCase;

    @MockBean
    private EditarLivroUseCase editarLivroUseCase;

    @MockBean
    private ExcluirLivroUseCase excluirLivroUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarLivro() throws Exception {

        CriarLivroRequest request = new CriarLivroRequest(
                "Clean Code",
                "Robert Martin",
                "123",
                "ISBN",
                "1ª edição",
                2
        );

        LivroResponseDTO response = new LivroResponseDTO(
                1L,
                "Clean Code",
                "Robert Martin",
                "123",
                "9780132350884",
                1,
                2
        );

        when(criarLivroUseCase.executar(any()))
                .thenReturn(response);

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Clean Code"));
    }

    @Test
    void deveRetornar400QuandoDadosInvalidos() throws Exception {

        String json = """
                {
                  "titulo": "",
                  "autor": "",
                  "codigoBN": "",
                  "isbn": "",
                  "edicao": "",
                  "quantidadeExemplares": 0
                }
                """;

        mockMvc.perform(post("/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.titulo").exists())
                .andExpect(jsonPath("$.autor").exists())
                .andExpect(jsonPath("$.codigoBN").exists())
                .andExpect(jsonPath("$.isbn").exists());
    }

    @Test
    void deveBuscarPorId() throws Exception {

        LivroDetalhadoResponseDTO response =
                new LivroDetalhadoResponseDTO(
                        1L,
                        "Clean Code",
                        "Robert Martin",
                        List.of()
                );

        when(buscarLivroPorIdUseCase.executar(1L))
                .thenReturn(response);

        mockMvc.perform(get("/livros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Clean Code"));
    }

    @Test
    void deveListarLivros() throws Exception {

        when(listarLivrosUseCase.executar())
                .thenReturn(List.of());

        mockMvc.perform(get("/livros"))
                .andExpect(status().isOk());
    }
}
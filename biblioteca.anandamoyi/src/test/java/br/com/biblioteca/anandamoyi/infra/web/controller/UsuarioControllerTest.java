package br.com.biblioteca.anandamoyi.infra.web.controller;

import br.com.biblioteca.anandamoyi.application.usecase.BuscarLivroPorIdUseCase;
import br.com.biblioteca.anandamoyi.application.usuario.BuscarUsuarioPorIdUseCase;
import br.com.biblioteca.anandamoyi.application.usuario.CriarUsuarioUseCase;
import br.com.biblioteca.anandamoyi.application.usuario.ListarUsuarioUseCase;
import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.infra.security.JwtFilter;
import br.com.biblioteca.anandamoyi.infra.security.JwtService;
import br.com.biblioteca.anandamoyi.infra.web.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtFilter jwtFilter;

    @MockBean
    JwtService jwtService;

    @MockBean
    private CriarUsuarioUseCase criarUsuarioUseCase;

    @MockBean
    private BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase;

    @MockBean
    private ListarUsuarioUseCase listarUsuarioUseCase;

    @MockBean
    private BuscarLivroPorIdUseCase buscarLivroPorIdUseCase;

    @Test
    void deveCriarUsuario() throws Exception {

        String json = """
        {
            "nome": "João",
            "email": "joao@email.com",
            "senha": "123456",
            "role": "LEITOR",
            "ativo": true
        }
        """;

        Usuario usuarioMock = new Usuario(
                1L,
                "João",
                "joao@email.com",
                "senhaHash",
                Role.LEITOR,
                true
        );

        when(criarUsuarioUseCase.executar(any()))
                .thenReturn(usuarioMock);

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("joao@email.com"));
    }

    @Test
    void deveRetornarErro400QuandoDadosInvalidos() throws Exception {

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nome").exists())
                .andExpect(jsonPath("$.email").exists())
                .andExpect(jsonPath("$.senha").exists())
                .andExpect(jsonPath("$.role").exists());
    }

    @Test
    void deveListarUsuarios() throws Exception {

        when(listarUsuarioUseCase.executar())
                .thenReturn(List.of());

        mockMvc.perform(get("/usuarios"))
                .andExpect(status().isOk());
    }
}
package br.com.biblioteca.anandamoyi.infra.web.controller;

import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import br.com.biblioteca.anandamoyi.infra.security.JwtFilter;
import br.com.biblioteca.anandamoyi.infra.security.JwtService;
import br.com.biblioteca.anandamoyi.infra.web.auth.AuthController;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = AuthController.class,
        excludeAutoConfiguration = {
                SecurityAutoConfiguration.class,
                SecurityFilterAutoConfiguration.class
        }
)
@AutoConfigureMockMvc(addFilters = false)
@Import(GlobalExceptionHandler.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    JwtFilter jwtFilter;

    @MockBean
    JwtService jwtService;

    @MockBean
    UsuarioRepository usuarioRepository;

    @MockBean
    PasswordEncoder passwordEncoder;

    @Test
    void deveAutenticarUsuarioComSucesso() throws Exception {

        Usuario usuario = new Usuario(
                1L,
                "Admin",
                "admin@test.com",
                "senha-criptografada",
                null,
                true
        );

        when(usuarioRepository.buscarPorEmail(anyString()))
                .thenReturn(Optional.of(usuario));

        when(passwordEncoder.matches(anyString(), anyString()))
                .thenReturn(true);

        when(jwtService.gerarToken(usuario))
                .thenReturn("jwt-token-teste");

        String json = """
                {
                  "email": "admin@test.com",
                  "senha": "123456"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwt-token-teste"));
    }

    @Test
    void deveRetornar401QuandoSenhaInvalida() throws Exception {

        Usuario usuario = new Usuario(
                1L,
                "Admin",
                "admin@test.com",
                "senha-criptografada",
                null,
                true
        );

        when(usuarioRepository.buscarPorEmail(anyString()))
                .thenReturn(Optional.of(usuario));

        when(passwordEncoder.matches(anyString(), anyString()))
                .thenReturn(false);

        String json = """
                {
                  "email": "admin@test.com",
                  "senha": "senha-errada"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deveRetornar401QuandoUsuarioNaoExistir() throws Exception {

        when(usuarioRepository.buscarPorEmail(anyString()))
                .thenReturn(Optional.empty());

        String json = """
                {
                  "email": "naoexiste@test.com",
                  "senha": "123456"
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deveRetornar400QuandoCamposInvalidos() throws Exception {

        String json = """
                {
                  "email": "",
                  "senha": ""
                }
                """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
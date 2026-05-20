package br.com.biblioteca.anandamoyi.application.usuario;

import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@TestPropertySource(properties = {
        "app.bootstrap.enabled=false"
})
class CriarUsuarioIntegrationTest {

    @Autowired
    private CriarUsuarioUseCase criarUsuarioUseCase; // REAL

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        when(passwordEncoder.encode(any()))
                .thenReturn("senha_criptografada");
    }

    // =========================
    // TESTE 1 - USE CASE
    // =========================
    @Test
    void deveCriarUsuarioNoBanco() {

        CriarUsuarioCommand command =
                new CriarUsuarioCommand(
                        "Maria",
                        "maria@email.com",
                        "123456",
                        Role.LEITOR,
                        true
                );

        Usuario usuario = criarUsuarioUseCase.executar(command);

        assertNotNull(usuario.getId());

        Optional<Usuario> usuarioSalvo =
                usuarioRepository.buscarPorEmail("maria@email.com");

        assertTrue(usuarioSalvo.isPresent());
    }

    // =========================
    // TESTE 2 - API
    // =========================
    @Test
    void deveCriarUsuario() throws Exception {

        String json = """
        {
            "nome": "Maria",
            "email": "maria2@email.com",
            "senha": "123456",
            "role": "LEITOR",
            "ativo": true
        }
        """;

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }

    // =========================
    // TESTE 3 - EMAIL DUPLICADO
    // =========================
    @Test
    void deveRetornarErroEmailDuplicado() throws Exception {

        String json = """
        {
            "nome": "Maria",
            "email": "duplicado@email.com",
            "senha": "123456",
            "role": "LEITOR",
            "ativo": true
        }
        """;

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        mockMvc.perform(post("/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}
package br.com.biblioteca.anandamoyi.infra.security;

import br.com.biblioteca.anandamoyi.domain.enums.Role;
import br.com.biblioteca.anandamoyi.domain.repository.UsuarioRepository;
import br.com.biblioteca.anandamoyi.domain.usuario.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SecurityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    private String adminToken;
    private String leitorToken;

    @BeforeEach
    void setup() {

        // LIMPA O BANCO
        usuarioRepository.deletarTodos();

        Usuario admin = new Usuario(
                null,
                "Admin",
                "admin@test.com",
                passwordEncoder.encode("123456"),
                Role.ADMIN,
                true
        );

        Usuario leitor = new Usuario(
                null,
                "Leitor",
                "leitor@test.com",
                passwordEncoder.encode("123456"),
                Role.LEITOR,
                true
        );

        admin = usuarioRepository.salvar(admin);
        leitor = usuarioRepository.salvar(leitor);

        adminToken = jwtService.gerarToken(admin);
        leitorToken = jwtService.gerarToken(leitor);
    }

    @Test
    void deveRetornar401SemToken() throws Exception {

        mockMvc.perform(get("/livros"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void leitorNaoPodeCriarLivro() throws Exception {

        String json = """
                {
                  "titulo": "Livro Teste",
                  "autor": "Autor Teste",
                  "codigoBN": "BN999",
                  "isbn": "9780000000000",
                  "edicao": "1",
                  "quantidadeExemplares": 1
                }
                """;

        mockMvc.perform(post("/livros")
                        .header("Authorization", "Bearer " + leitorToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminPodeCriarLivro() throws Exception {

        String json = """
                {
                  "titulo": "Livro Admin",
                  "autor": "Autor Admin",
                  "codigoBN": "BN1000_ADMIN",
                  "isbn": "9781111111111",
                  "edicao": "1",
                  "quantidadeExemplares": 2
                }
                """;

        mockMvc.perform(post("/livros")
                        .header("Authorization", "Bearer " + adminToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(result -> {
                    System.out.println("STATUS: " + result.getResponse().getStatus());
                    System.out.println("BODY: " + result.getResponse().getContentAsString());
                })
                .andExpect(status().isCreated());
    }

    @Test
    void tokenInvalidoDeveRetornar401() throws Exception {

        mockMvc.perform(get("/livros")
                        .header("Authorization", "Bearer token.invalido"))
                .andExpect(status().isUnauthorized());
    }
}
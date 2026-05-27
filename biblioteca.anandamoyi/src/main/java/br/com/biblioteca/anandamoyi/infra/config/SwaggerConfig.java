package br.com.biblioteca.anandamoyi.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()

                // JWT
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList(SECURITY_SCHEME_NAME)
                )

                .schemaRequirement(
                        SECURITY_SCHEME_NAME,

                        new SecurityScheme()
                                .name(SECURITY_SCHEME_NAME)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Insira o token JWT no formato: Bearer {token}")
                )

                // INFO API
                .info(
                        new Info()
                                .title("Biblioteca Mestre Ramatis API")
                                .version("1.0.0")
                                .description("""
                                        API REST para gerenciamento de biblioteca.

                                        Funcionalidades:
                                        - autenticação JWT
                                        - cadastro de usuários
                                        - cadastro de livros
                                        - empréstimos
                                        - devoluções
                                        - multas automáticas
                                        - controle de atraso
                                        - controle de permissões

                                        Projeto desenvolvido com:
                                        - Spring Boot
                                        - Spring Security
                                        - JWT
                                        - PostgreSQL
                                        - Docker
                                        - JaCoCo
                                        - Swagger/OpenAPI
                                        """)
                                .contact(
                                        new Contact()
                                                .name("Leandro Gonçalves")
                                                .url("https://github.com/CabeloSG")
                                )
                                .license(
                                        new License()
                                                .name("Projeto Educacional")
                                )
                );
    }
}
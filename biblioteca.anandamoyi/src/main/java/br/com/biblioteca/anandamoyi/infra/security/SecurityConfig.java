package br.com.biblioteca.anandamoyi.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Profile("!test")
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(
            JwtFilter jwtFilter,
            UserDetailsService userDetailsService
    ) {
        this.jwtFilter = jwtFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

                //  DESABILITA CSRF (API REST)
                .csrf(AbstractHttpConfigurer::disable)

                //  SEM SESSÃO (JWT)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                //  REGRAS DE ACESSO
                .authorizeHttpRequests(auth -> auth

                        //  ROTAS PUBLICAS
                        .requestMatchers(
                                "/auth/**",
                                "/h2-console/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()

                        //  PERMITE CRIAR USUARIO (IMPORTANTE PARA TESTE)
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                        //  PROTEGIDO (PRECISA TOKEN)
                        .requestMatchers("/usuarios/**").authenticated()

                        //  ADMIN
                        .requestMatchers("/admin/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                //  TRATAMENTO CORRETO DOS ERROS
                .exceptionHandling(ex -> ex

                        // 🔐 401 - NÃO AUTENTICADO
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Não autenticado\"}");
                        })

                        // 🔐 403 - SEM PERMISSÃO (AccessDeniedException)
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            response.setContentType("application/json");
                            response.getWriter().write("{\"error\":\"Acesso negado\"}");
                        })
                )

                //  USER DETAILS
                .userDetailsService(userDetailsService)

                //  JWT FILTER
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                //  H2 CONSOLE
                .headers(headers ->
                        headers.frameOptions(frame -> frame.disable())
                )

                .build();
    }

    //  ENCODER
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //  AUTH MANAGER
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }
}
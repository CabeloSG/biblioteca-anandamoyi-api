package br.com.biblioteca.anandamoyi.application.usuario;


import br.com.biblioteca.anandamoyi.application.usuario.AutenticarUsuarioUseCase;


public interface AutenticarUsuarioUseCase {
    String executar(String email, String senha);
}
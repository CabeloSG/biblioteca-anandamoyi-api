package br.com.biblioteca.anandamoyi.domain.exception;

import br.com.biblioteca.anandamoyi.application.dto.ExemplarResponseDTO;

public class NenhumExemplarDisponivelException extends RuntimeException{

    public NenhumExemplarDisponivelException(Long id){
        super("Nenhum exemplar disponível para a edição: " + id);
    }

}

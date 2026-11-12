package br.com.jsncartoesms.dto;

import java.util.UUID;

public record OutboxMensagemDto(

        UUID id,
        String agregadoeId,
        String topico,
        String payload
){

    
}

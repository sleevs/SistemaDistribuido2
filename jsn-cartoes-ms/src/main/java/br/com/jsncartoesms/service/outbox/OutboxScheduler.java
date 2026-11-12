package br.com.jsncartoesms.service.outbox;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.jsncartoesms.dto.OutboxMensagemDto;

@Component 
public class OutboxScheduler {

    private final OutboxReservaService reservaService;
    private final OutboxPublicacaoService outboxPublicacaoService;


    public OutboxScheduler(OutboxReservaService reservaService,OutboxPublicacaoService outboxPublicacaoService){
        this.reservaService = reservaService;
        this.outboxPublicacaoService = outboxPublicacaoService;
    }

    @Scheduled (fixedDelay = 1000)
    public void processarOutbox(){
        List<OutboxMensagemDto> eventos = reservaService.reservar(50);
        
        if(eventos.isEmpty()){
            return ;
        }

        System.out.println("LOG INFO - PROCESSANDO OUTBOX EVENTO : " + eventos.size());
        
        eventos.forEach(outboxPublicacaoService::publicar);
    }
    
}

package br.com.jsncartoesms.service.outbox;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.jsncartoesms.dto.OutboxMensagemDto;
import br.com.jsncartoesms.entity.OutboxEntity;
import br.com.jsncartoesms.repository.OutboxRepository;
import br.com.jsncartoesms.util.OutboxStatus;
import jakarta.transaction.Transactional;

@Service 
public class OutboxReservaService {
    

    private OutboxRepository outboxRepository;

    public OutboxReservaService(OutboxRepository outboxRepository){
        this.outboxRepository = outboxRepository;
    }

    @Transactional 
    public List<OutboxMensagemDto> reservar(int workerSize){
        

        List<OutboxEntity> eventos = outboxRepository.buscarEventosDisponiveis(workerSize);
        OffsetDateTime dataTime = OffsetDateTime.now().plusMinutes(2);
        List<OutboxMensagemDto> mensagems = new ArrayList<>();
        

        for(OutboxEntity evento : eventos){
            evento.setStatus(OutboxStatus.PROCESSANDO);
            evento.setBloqueadoAte(dataTime);

            mensagems.add(new OutboxMensagemDto(
                evento.getId(),
                evento.getAgregador(),
                evento.getTopico(),
                evento.getPayload()
            ));

            outboxRepository.flush();
        }

        return  mensagems;

        
    }
}

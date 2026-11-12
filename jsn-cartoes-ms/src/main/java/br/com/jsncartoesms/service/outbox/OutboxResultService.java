package br.com.jsncartoesms.service.outbox;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.springframework.stereotype.Service;

import br.com.jsncartoesms.entity.OutboxEntity;
import br.com.jsncartoesms.repository.OutboxRepository;
import br.com.jsncartoesms.util.OutboxStatus;
import jakarta.transaction.Transactional;

/**
 * OutboxResultService
 */
@Service 
public class OutboxResultService {

    private static final int MAX_TENTATIVA = 5 ;
    private final OutboxRepository outboxRepository;

    public OutboxResultService(OutboxRepository outboxRepository){
        this.outboxRepository = outboxRepository;
    }


    @Transactional 
    public void publicar(UUID id){

        outboxRepository.marcarPublicacao(id, OffsetDateTime.now());
    }


    @Transactional 
    public void scheduler(UUID id , String erro){

        OutboxEntity outboxEntity = outboxRepository.findById(id).orElseThrow();
        int tentativas = outboxEntity.getTentativa() + 1;

        outboxEntity.setTentativa(tentativas);
        outboxEntity.setUltimoErro(erro);
        outboxEntity.setBloqueadoAte(null);

        if(tentativas >= MAX_TENTATIVA){
            outboxEntity.setStatus(OutboxStatus.DEAD_LETTER);
            return ;    
        }
        long delaySeconds = Math.min(30L * (1L << (tentativas  - 1)) , 900L);
        outboxEntity.setStatus(OutboxStatus.PENDENTE);
        outboxEntity.setProximaTentativaEm(OffsetDateTime.now().plusSeconds(delaySeconds));
        
    
    }
}

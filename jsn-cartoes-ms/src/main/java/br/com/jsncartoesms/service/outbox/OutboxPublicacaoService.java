package br.com.jsncartoesms.service.outbox;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import br.com.jsncartoesms.dto.OutboxMensagemDto;

@Service 
public class OutboxPublicacaoService {
    
    
    private final KafkaTemplate<String , String >kafkaTemplate;
    private final OutboxResultService resultService;

    public OutboxPublicacaoService(KafkaTemplate<String , String >kafkaTemplate , OutboxResultService resultService){
        this.kafkaTemplate = kafkaTemplate;
        this.resultService = resultService;
    }
    
    public void publicar(OutboxMensagemDto evento){

        try{
            kafkaTemplate.send(
                evento.topico(),
                evento.agregadoeId(),
                evento.payload()
            ).whenComplete((result , exception ) -> {
                if(exception == null){
                    resultService.publicar(evento.id());

                }else{
                    resultService.scheduler(evento.id() , exception.getMessage());
                }
            });
        }catch(Exception e){
            resultService.scheduler(evento.id(), e.getMessage());
        }
    }

}

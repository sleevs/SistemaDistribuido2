package br.com.jsnemissorms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jsnemissorms.dto.CartaoDto;



@Service
public class EmissaoProducer {
    


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Value("${kafka.topic.cartao}")
    String topicName;

    public EmissaoProducer(KafkaTemplate<String, String> kafkaTemplate,
                          @Value("${kafka.topic.cartao}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }
    

    public void enviarEmissaoDeCartaoParaNotificacao(CartaoDto cartaoDto) {
        
        try{
            ObjectMapper  map = new ObjectMapper();
             String msg = map.writeValueAsString(cartaoDto);
             kafkaTemplate.send(topicName, msg);
             System.out.println("MENSAGEM ENVIADA " + msg);
        }catch(Exception e){

        }
    }
    
}

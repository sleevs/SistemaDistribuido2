package br.com.jsncartoesms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.jsncartoesms.dto.CartaoDto;
import br.com.jsncartoesms.dto.PropostaDto;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class CartaoProducer {

    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Value("${kafka.topic.cartao}")
    String topicName;

    public CartaoProducer(KafkaTemplate<String, String> kafkaTemplate,
                          @Value("${kafka.topic.cartao}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }
    

    public void enviarCartaoParaEmissao(PropostaDto proposta) {
        
        try{
            ObjectMapper  map = new ObjectMapper();
             String msg = map.writeValueAsString(proposta);
             kafkaTemplate.send(topicName, msg);
             System.out.println("MENSAGEM ENVIADA " + msg);
        }catch(Exception e){

        }
    }
    
}

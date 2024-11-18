package br.com.jsnemissorms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jsnemissorms.dto.CartaoDto;
import br.com.jsnemissorms.dto.PropostaDto;

@Service
public class EmissaoConsumer {

    @Autowired
    private EmissaoService emissorService;

     @Value("${kafka.topic.cartao}") 
     String topicName;

    @KafkaListener(topics = "cartao-topic", groupId = "emissor-group")
    public void consumirMensagem(String cartaoCredito) {
        // Aqui o microserviço emissor-ms processa a emissão e envia o email
        try{

        ObjectMapper map = new ObjectMapper();
        PropostaDto proposta = map.readValue(cartaoCredito, PropostaDto.class);
        System.out.println("MENSAGEM RECEBIDAS " + cartaoCredito);
        emissorService.enviarEmail(proposta);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

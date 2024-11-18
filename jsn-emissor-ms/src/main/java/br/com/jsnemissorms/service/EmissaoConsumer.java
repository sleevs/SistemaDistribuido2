package br.com.jsnemissorms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.jsnemissorms.dto.CartaoDto;

@Service
public class EmissaoConsumer {

    @Autowired
    private EmissaoService emissorService;

     @Value("${kafka.topic.cartao}") 
     String topicName;

    @KafkaListener(topics = "cartao-topic", groupId = "emissor-group")
    public void consumirMensagem(String cartaoCredito) {
        // Aqui o microserviço emissor-ms processa a emissão e envia o email
        System.out.println("MENSAGEM RECEBIDAS " + cartaoCredito);
       // emissorService.enviarEmail(cartaoCredito);
    }
    
}

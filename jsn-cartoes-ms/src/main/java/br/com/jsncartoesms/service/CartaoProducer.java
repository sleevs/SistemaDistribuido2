package br.com.jsncartoesms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.jsncartoesms.dto.CartaoDto;

@Service
public class CartaoProducer {

    
    @Autowired
    private KafkaTemplate<String, CartaoDto> kafkaTemplate;
    
    @Value("${kafka.topic.cartao}")
    String topicName;

    public CartaoProducer(KafkaTemplate<String, CartaoDto> kafkaTemplate,
                          @Value("${kafka.topic.cartao}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }
    

    public void enviarCartaoParaEmissao(CartaoDto cartaoCredito) {
        kafkaTemplate.send(topicName, cartaoCredito);
    }
    
}

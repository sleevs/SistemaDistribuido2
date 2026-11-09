package br.com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dto.RequestDto;

@Service
public class NotificacaoConsumer {
    

    private NotificacaoService notificacaoService;

     @Value("${kafka.topic.cartao}") 
     String topicName;

    @KafkaListener(topics = "cartao-emitido", groupId = "notification-group")
    public void consumirMensagem(String cartaoEmitido) {

        try{

        ObjectMapper map = new ObjectMapper();
        RequestDto notificarCartaoEmitido = map.readValue(cartaoEmitido, RequestDto.class);
        System.out.println("MENSAGEM RECEBIDAS " + notificarCartaoEmitido.getNome() + notificarCartaoEmitido.getBanco());
       
        //outbox pattarn aqui 

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
package br.com.jsncartoesms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.jsncartoesms.dto.CartaoDto;

@Service
public class CartaoService {



    /*
     * criar cartao de credito 
     * registrar no banco de dados
     * realizar chamada para o servicço emissao de cartão
     * 
     */
    public void criarCartaoDeCredito(CartaoDto cartao){
        
    }


    
}

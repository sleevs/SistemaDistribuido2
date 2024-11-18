package br.com.jsncartoesms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.jsncartoesms.dto.PropostaDto;

@Service
public class CartaoService {


    @Autowired
    private CartaoProducer CartaoProducer;

    /*
     * criar cartao de credito 
     * registrar no banco de dados
     * realizar chamada para o servicço emissao de cartão
     * 
     */
    public PropostaDto criarProposta(PropostaDto proposta){
       
        if(proposta.getLimite().intValue() <= 1000){
            CartaoProducer.enviarCartaoParaEmissao(proposta);
            return proposta;
        }
        return null;
    }


    
}

package br.com.jsncartoesms.service.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.jsncartoesms.dto.PropostaCartaoDto;

@Service
public class PropostaCartaoService {


    @Autowired
    private PropostaCartaoProducer CartaoProducer;

    /*
     * criar cartao de credito 
     * registrar no banco de dados
     * realizar chamada para o servicço emissao de cartão
     * 
     */
    public PropostaCartaoDto criarProposta(PropostaCartaoDto proposta){
       
        if(proposta.getLimite().intValue() <= 1000){
            CartaoProducer.enviarCartaoParaEmissao(proposta);
            return proposta;
        }
        return null;
    }


    
}

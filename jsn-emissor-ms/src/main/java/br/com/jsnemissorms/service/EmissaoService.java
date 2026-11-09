package br.com.jsnemissorms.service;

import java.security.SecureRandom;
import org.springframework.stereotype.Service;
import br.com.jsnemissorms.dto.CartaoDto;
import br.com.jsnemissorms.dto.PropostaDto;

@Service
public class EmissaoService {
    
    
    private  EmissaoProducer emissaoProducer;

    public EmissaoService( EmissaoProducer emissaoProducer){
        this.emissaoProducer = emissaoProducer;
    }

    
public  Long gerarNumeroDoCartao() {

    SecureRandom random = new SecureRandom();

    int numberOfDigits = 10;

    long randomNumber = 0;
    for (int i = 0; i < numberOfDigits; i++) {
        randomNumber = randomNumber * 10 + random.nextInt(10);
    }

    randomNumber = randomNumber + (long) Math.pow(10, numberOfDigits - 1);

  return randomNumber; 
}


    public CartaoDto criarCartao(PropostaDto dto){

       try{
        CartaoDto cartao = new CartaoDto();
        cartao.setNumero(gerarNumeroDoCartao());
        cartao.setValidade("21/12/2034");
        cartao.setLimite(dto.getLimite());
        cartao.setBanco(dto.getBanco());
        cartao.setBandeira("MASTERCARD");
        cartao.setTipo(dto.getTipo());
        cartao.setNome(dto.getNome());
        enviarNotificarcao(cartao);
        return cartao;
       }catch(Exception e){
        e.printStackTrace();
       }
       return null;
    }


    public void enviarNotificarcao(CartaoDto dto ){

        System.out.println("CARTAO ENVIADO PARA "+ dto);
       emissaoProducer.enviarEmissaoDeCartaoParaNotificacao(dto);

    }




    
}

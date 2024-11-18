package br.com.jsnemissorms.service;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jsnemissorms.dto.CartaoDto;
import br.com.jsnemissorms.dto.PropostaDto;
import br.com.jsnemissorms.exception.EnviarEmailException;
import br.com.jsnemissorms.exception.JsonExcepion;

@Service
public class EmissaoService {
    
    @Autowired
    private  JavaMailSender emailSender;

    public EmissaoService(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    public void enviarEmail(PropostaDto dto ){

       try{
        SimpleMailMessage message = new SimpleMailMessage();
        CartaoDto novoCartao  =  criarCartao(dto);
        message.setTo("jsn_13_13@hotmail.com");
        message.setSubject("Seu novo Cartão de Crédito");
        message.setText("Informações do seu cartão: "+ novoCartao );
        ObjectMapper map = new ObjectMapper();
        String result = map.writeValueAsString(novoCartao);
        System.out.println("CARTAO ENVIADO PARA "+ result);
       // emailSender.send(message);
       }catch(MailException e){
        e.printStackTrace();
        throw new EnviarEmailException("Exception ocorreu ao tentar enviar email "+e.getMessage());
       }
       catch (JsonProcessingException e) {
        throw new JsonExcepion("Erro ao tentar converter Objeto para String " + e.getMessage());

      }

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
        return cartao;
       }catch(Exception e){
        e.printStackTrace();
       }
       return null;
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
    
}

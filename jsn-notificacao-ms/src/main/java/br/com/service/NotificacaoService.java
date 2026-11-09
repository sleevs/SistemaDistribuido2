package br.com.service;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.dto.RequestDto;
import br.com.dto.ResponseDto;
import br.com.repository.NotificacaoRepository;

@Service 
public class NotificacaoService {


    private  JavaMailSender emailSender;
    private NotificacaoRepository notificacaoRepository;


    public NotificacaoService(NotificacaoRepository notificacaoRepository , JavaMailSender emailSender){
        this.notificacaoRepository = notificacaoRepository;
        this.emailSender = emailSender;
    }

    
    public ResponseDto notificarCliente(RequestDto requestDto){

        ResponseDto responseDto = new ResponseDto();
        //responseDto.setDesc(requestDto.getNome());

        System.out.println("CARTAO ENVIADO PARA "+ requestDto.getNome());
        return responseDto;
    }


    
     public void enviarEmail(RequestDto dto ){

       try{
        
        SimpleMailMessage message = new SimpleMailMessage();
       
        message.setTo("jsn_13_13@hotmail.com");
        message.setSubject("Seu novo Cartão de Crédito");
      //  message.setText("Informações do seu cartão: "+ novoCartao );
        ObjectMapper map = new ObjectMapper();
      //  String result = map.writeValueAsString(novoCartao);
      //  System.out.println("CARTAO ENVIADO PARA "+ result);
       // emailSender.send(message);
        
       }catch(MailException e){
        e.printStackTrace();
     //   throw new EnviarEmailException("Exception ocorreu ao tentar enviar email "+e.getMessage());
       }
       catch (Exception e) {
    //    throw new JsonExcepion("Erro ao tentar converter Objeto para String " + e.getMessage());

      }

    }

    
}

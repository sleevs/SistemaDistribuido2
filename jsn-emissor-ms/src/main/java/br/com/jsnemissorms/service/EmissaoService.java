package br.com.jsnemissorms.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.jsnemissorms.dto.CartaoDto;

@Service
public class EmissaoService {
    

    private  JavaMailSender emailSender;

    public EmissaoService(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    public void enviarEmail(CartaoDto dto){

          SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("cliente@example.com");
        message.setSubject("Seu novo Cartão de Crédito");
        message.setText("Informações do seu cartão: \nNúmero: " + dto.getNumero() + 
                        "\nValidade: " + dto.getValidade());
        emailSender.send(message);

    }

    
}

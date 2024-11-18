package br.com.jsnemissorms.exception;

public class EnviarEmailException extends RuntimeException{

    public EnviarEmailException(String mensagem){
        super(mensagem);
    }
    
}

package br.com.jsncartoesms.dto;

import java.math.BigDecimal;

public class PropostaDto {
    

    private String clientNome;
    private BigDecimal limite;

    public PropostaDto(){

    }

    public String getClientNome() {
        return clientNome;
    }

    public void setClientNome(String clientNome) {
        this.clientNome = clientNome;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }

    
}

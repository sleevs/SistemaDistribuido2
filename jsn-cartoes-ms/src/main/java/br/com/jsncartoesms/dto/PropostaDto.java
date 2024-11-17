package br.com.jsncartoesms.dto;

public class PropostaDto {
    

    private String clientNome;
    private Double limite;

    public PropostaDto(){

    }

    public String getClientNome() {
        return clientNome;
    }

    public void setClientNome(String clientNome) {
        this.clientNome = clientNome;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    
}

package br.com.jsncartoesms.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropostaCartaoDto {
    
    @JsonProperty("Nome")
    private String nome;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Valor limite")
    private BigDecimal limite;
    @JsonProperty("Nome do Banco")
    private String banco;
    @JsonProperty("Crédito ou Débito")
    private String tipo;

    public PropostaCartaoDto(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}

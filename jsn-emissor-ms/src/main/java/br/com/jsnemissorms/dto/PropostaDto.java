package br.com.jsnemissorms.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropostaDto {
    

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

    public PropostaDto(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setLimite(BigDecimal limite) {
        this.limite = limite;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    

}
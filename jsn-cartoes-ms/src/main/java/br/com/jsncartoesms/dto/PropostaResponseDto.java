package br.com.jsncartoesms.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropostaResponseDto {

  
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
    @JsonProperty("Numero")
    private String numero;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Data")
    private String data;


    public PropostaResponseDto(){}


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
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


    public String getNumero() {
        return numero;
    }


    public void setNumero(String numero) {
        this.numero = numero;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getData() {
        return data;
    }


    public void setData(String data) {
        this.data = data;
    }

    
}

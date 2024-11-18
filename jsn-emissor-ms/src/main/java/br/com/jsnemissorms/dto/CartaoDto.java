package br.com.jsnemissorms.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartaoDto {

    @JsonProperty("Nome do proprietário")
    private String nome;
    @JsonProperty("Número do Cartão")
    private Long numero;
    @JsonProperty("Data de validade")
    private String validade;
    @JsonProperty("Limite de crédito")
    private BigDecimal limite;
    @JsonProperty("Tipo do cartão")
    private String tipo;
    @JsonProperty("Bandeira do cartão")
    private String bandeira;
    @JsonProperty("Banco associado ao cartão")
    private String banco;

    public CartaoDto(){

    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    @Override
    public String toString() {
        return "Cartão [numero=" + numero + ", validade=" + validade + ", nome=" + nome + ", limite=" + limite
                + ", tipo=" + tipo + ", bandeira=" + bandeira + ", banco=" + banco + "]";
    }

    

    
}

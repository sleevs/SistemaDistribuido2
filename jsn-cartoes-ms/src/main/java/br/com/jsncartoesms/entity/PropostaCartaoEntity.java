package br.com.jsncartoesms.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;

@Table(name = "proposta")
@Entity
public class PropostaCartaoEntity{

    @Id
    private UUID id ;
    @Column (nullable = false)
    private String nome;
    @Column (nullable = false)
    private String email;
    @Column (nullable = false)
    private BigDecimal limite;
    @Column (nullable = false)
    private String banco;
    @Column (nullable = false)
    private String tipo;

    public PropostaCartaoEntity(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    
}
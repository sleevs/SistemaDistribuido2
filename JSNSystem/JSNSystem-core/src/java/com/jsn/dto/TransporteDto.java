/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.dto;

import java.util.Date;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jason Sleeves
 */
@XmlRootElement
public class TransporteDto {
    
    private String nome ;
    private String tipo ;
    private Date ano ;
    private String categoria ;
    private Integer kilometrgem ;
    private String placa ;
    
    
    
    public String getNome() {
        return nome;
    }

    @XmlElement
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    @XmlElement
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getAno() {
        return ano;
    }

    @XmlElement
    public void setAno(Date ano) {
        this.ano = ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getKilometrgem() {
        return kilometrgem;
    }

    @XmlElement
    public void setKilometrgem(Integer kilometrgem) {
        this.kilometrgem = kilometrgem;
    }

    public String getPlaca() {
        return placa;
    }

     @XmlElement
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    
    
    
    
    
}

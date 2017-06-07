/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.dao;

import com.jsn.business.TransporteBusiness;
import com.jsn.conversor.Conversor;
import com.jsn.dto.TransporteDto;
import com.jsn.modelo.Transporte;
import javax.ejb.EJB;

/**
 *
 * @author ADMINIBM
 */
public class TransporteDao {

    @EJB
    private TransporteBusiness transporteBusiness;

    public TransporteDao() {
    }

    public String criar(TransporteDto dto) {

        Transporte transporte = null;
        try {

            transporte = new Transporte();

            transporte = Conversor.converterDTOparaEntidade(dto);
            return transporteBusiness.transformarXml(transporte);

        } catch (Exception e) {

        }
        return null;
    }

    public void remover() {
    }

    public void editar() {
    }

    public void buscar() {
    }

    public void listar() {
    }

}

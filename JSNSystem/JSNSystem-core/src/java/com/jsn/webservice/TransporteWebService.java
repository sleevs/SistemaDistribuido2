/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.webservice;

import com.jsn.business.TransporteBusiness;
import com.jsn.conversor.Conversor;
import com.jsn.dao.TransporteDao;
import com.jsn.dto.TransporteDto;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Jason Sleeves
 */


@WebService
@Stateless
public class TransporteWebService implements TransporteControleSoap{


     
        @EJB
    private TransporteBusiness transporteBusiness ;
     
    public TransporteWebService() {
    }

    @Override
    public String insertTransporte(TransporteDto dto) {

    //  TransporteDao  transportedao  = new TransporteDao();
     //   return transportedao.criar(dto);
     
    return  transporteBusiness.transformarXml(Conversor.converterDTOparaEntidade(dto));

    }

    @Override
    public TransporteDto buscarTipoTransporte(TransporteDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TransporteDto> todosTransporte(TransporteDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
   
    
}

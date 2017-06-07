/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.webservice;

import com.jsn.dto.TransporteDto;
import java.util.Date;
import java.util.List;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Jason Sleeves
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TransporteControleSoap {
    
    public String insertTransporte(TransporteDto dto);

    public TransporteDto buscarTipoTransporte(TransporteDto dto);

    public List<TransporteDto> todosTransporte(TransporteDto dto);

}

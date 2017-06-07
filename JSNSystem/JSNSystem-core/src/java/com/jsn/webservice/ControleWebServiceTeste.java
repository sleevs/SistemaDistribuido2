/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.webservice;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author ADMINIBM
 */



@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
@Stateless
public class ControleWebServiceTeste implements java.rmi.Remote {
    
    
  
    
    
    
    @WebMethod 
    public boolean ManterFabricanteFrontRequest(String EmpresaCodigo ,String FabricanteBloqueado , String FabricanteCNPJ , String FabricanteCodigo , String FabricanteEstrangeiro , String FabricanteNome){
    
    return false;
    }
}

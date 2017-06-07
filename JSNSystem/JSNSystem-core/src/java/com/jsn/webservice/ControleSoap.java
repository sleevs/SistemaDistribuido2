/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.webservice;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author Jason Sleeves
 */

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ControleSoap extends java.rmi.Remote{
    
    
    
    
    public String testeBroke (String nome , Integer valor);
    
    
    
    public String testeBroke2 (String nome , Integer valor);
    
    
     public String testeBroke3 (String nome , Integer valor);
    
    
    
    
}

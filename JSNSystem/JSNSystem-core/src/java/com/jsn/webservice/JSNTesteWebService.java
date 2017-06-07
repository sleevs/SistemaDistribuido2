/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.webservice;

import javax.ejb.Stateless;
import javax.jws.WebService;
/**
 *
 * @author Jason Sleeves
 */
@WebService(serviceName = "JSNTesteWebService")
@Stateless
public class JSNTesteWebService implements ControleSoap {

    @Override
    public String testeBroke(String nome, Integer valor) {

        return nome + valor.toString();

    }

    @Override
    public String testeBroke2(String nome, Integer valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String testeBroke3(String nome, Integer valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

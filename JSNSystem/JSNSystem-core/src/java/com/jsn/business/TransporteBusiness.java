/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.business;

import com.jsn.modelo.Transporte;
import java.io.File;
import java.io.StringWriter;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

/**
 *
 * @author Jason Sleeves
 */
@Stateless
public class TransporteBusiness {
    
    
      public String transformarXml(Transporte t) {

        try {

            JAXBElement<Transporte> jaxbElement = new JAXBElement(
                    new QName(Transporte.class.getSimpleName()), Transporte.class, t);

            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Transporte.class);

            context.createMarshaller().marshal(jaxbElement, writer);
            System.out.println(writer.toString());

            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Transporte transformarObjeto(String xml) {

        try {

            JAXBContext context = JAXBContext.newInstance(Transporte.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();

            Transporte transporte = (Transporte) unmarshaller.unmarshal(new File(xml));
            
            return transporte ;
        } catch (JAXBException e) {

        }

        return null;
    }

}

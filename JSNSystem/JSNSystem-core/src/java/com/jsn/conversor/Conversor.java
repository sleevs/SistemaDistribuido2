/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsn.conversor;

import com.jsn.dto.TransporteDto;
import com.jsn.modelo.Transporte;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jason Sleeves
 */
public class Conversor {
    
    public static Transporte converterDTOparaEntidade(TransporteDto dto) {

        Transporte transporte = new Transporte();

        if (dto != null) {

            transporte.setTipo(dto.getTipo());
            transporte.setNome(dto.getNome());
            transporte.setKilometrgem(dto.getKilometrgem());
            transporte.setCategoria(dto.getCategoria());
            transporte.setAno(dto.getAno());
            transporte.setPlaca(dto.getPlaca());

            return transporte;

        }

        return null;
    }

    public static TransporteDto converterEntidadeParaDTO(Transporte entidade) {

        TransporteDto transporteDto = new TransporteDto();

        if (entidade != null) {

            transporteDto.setTipo(entidade.getTipo());
            transporteDto.setNome(entidade.getNome());
            transporteDto.setKilometrgem(entidade.getKilometrgem());
            transporteDto.setCategoria(entidade.getCategoria());
            transporteDto.setAno(entidade.getAno());
            transporteDto.setPlaca(entidade.getPlaca());

            return transporteDto;

        }

        return null;

    }

    
     public static List<TransporteDto> converterListaEntidadeParaListaDTO(List<Transporte> lsitaEntidade) {
     
     
     List<TransporteDto> listaDto = new ArrayList<>();
     TransporteDto transporteDto = new TransporteDto();

     for (Transporte entidade : lsitaEntidade ){
     
         transporteDto = converterEntidadeParaDTO(entidade);
         listaDto.add(transporteDto);
         
    
     }
     return listaDto ; 
     }
    
    
     
     public static List<Transporte> converterListaDTOParaListaEntidade(List<TransporteDto>  lsitaDto) {
     
     
     List<Transporte> listaEntidade = new ArrayList<>();
     Transporte entidade = new Transporte();

     for (TransporteDto dto : lsitaDto ){
     
         entidade = converterDTOparaEntidade(dto);
         listaEntidade.add(entidade);
         
    
     }
     return listaEntidade ; 
     }
    
    
    

}

package br.com.jsncartoesms.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import br.com.jsncartoesms.dto.PropostaDto;

public abstract class Validador {



private static final Predicate<String> isNullOrEmpty = s ->  s == null || s.isEmpty();


    public static Map<String,Boolean> verificar(PropostaDto dto) {
        Map<String,Boolean> map = new HashMap<>();
        if (isNullOrEmpty.test(dto.getBanco())) {
            map.put("Nome do banco não pode ser nulo",false);
            return map;
        }
        if (isNullOrEmpty.test(dto.getTipo())) {
            map.put("A informação sobre o Tipo do cartão não pode ser nula",false);
            return map;
        }
        if (isNullOrEmpty.test(dto.getEmail())) {
            map.put("É necessário informar um email valído",false);
            return map;
        }
        if (isNullOrEmpty.test(dto.getNome())) {
            map.put("É necessário informar o nome corretamente",false);
            return map;
        }

        if (dto.getLimite() == null || dto.getLimite().compareTo(BigDecimal.ZERO) < 0) {
            map.put("É necessário informar um valor de limite para o cartão",false);
            return map;
        }
        
        map.put("OK",true);
        return map;
    }
    
}

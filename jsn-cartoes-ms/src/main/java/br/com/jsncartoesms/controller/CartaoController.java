package br.com.jsncartoesms.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.jsncartoesms.dto.PropostaDto;
import br.com.jsncartoesms.service.CartaoService;
import br.com.jsncartoesms.util.Validador;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CartaoController {
    

    @Autowired
    private CartaoService cartaoService;

    @Operation(
    summary = "Criar proposta de crédito",
    description = "Informe os dados básicos para criação da proposta de crédito .",
    tags = {"Criar proposta de crédito"} 
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Proposta de crédito criada com sucesso"),
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/criar-proposta")
    public ResponseEntity<Object> criarProposta(@RequestBody PropostaDto proposta) {

        try{
            Map<String,Boolean> verificar = Validador.verificar(proposta);
            if(!verificar.containsKey("OK")){
                
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO DE VALIDAÇÃO DE INPUT :" + verificar.keySet());
            }

            
            PropostaDto dto = cartaoService.criarProposta(proposta);
       
            if(dto != null){
                return ResponseEntity.ok(dto);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO NO PROCESSAMENTO DOS DADOS ");
      
        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO NO SERVIDOR A PROCESSAR ESSA REQUISIÇÃO.");
        }
    }

}

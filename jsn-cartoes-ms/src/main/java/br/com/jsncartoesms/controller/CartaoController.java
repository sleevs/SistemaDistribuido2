package br.com.jsncartoesms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.jsncartoesms.dto.CartaoDto;
import br.com.jsncartoesms.dto.ClienteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CartaoController {
    



    @Operation(
    summary = "Cadastrar Cliente",
    description = "Informe os dados básicos para cadastrar cliente.",
    tags = {"Cadastrar Cliente"} 
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Cadastro do cliente realizado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/cadastrar-cliente")
    public ResponseEntity<Object> criarCliente(@RequestBody ClienteDto clienteDto) {

        try{

            if(clienteDto == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO DE VALIDAÇÃO DE INPUT , O FORMULÁRIO DE ESTAR CORRETAMENTE PREENCHIDO.");
            }

            
        //    CartaoDto dto = accountService.create(account);
        CartaoDto dto = null;
            if(dto != null){
                return ResponseEntity.ok(dto);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO DE VALIDAÇÃO DE INPUT ");
      
        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO NO SERVIDOR A PROCESSAR ESSA REQUISIÇÃO.");
        }
    }



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
    public ResponseEntity<Object> criarProposta(@RequestBody CartaoDto cartaoDto) {

        try{

            if(cartaoDto == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO DE VALIDAÇÃO DE INPUT , O FORMULÁRIO DE ESTAR CORRETAMENTE PREENCHIDO.");
            }

            
        //    CartaoDto dto = accountService.create(account);
        CartaoDto dto = null;
            if(dto != null){
                return ResponseEntity.ok(dto);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO DE VALIDAÇÃO DE INPUT ");
      
        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO NO SERVIDOR A PROCESSAR ESSA REQUISIÇÃO.");
        }
    }

}

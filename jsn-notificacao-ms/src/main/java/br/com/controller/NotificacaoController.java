package br.com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dto.RequestDto;
import br.com.dto.ResponseDto;
import br.com.service.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController 
@RequestMapping 
public class NotificacaoController {
   

    private NotificacaoService  notificacaoService;

    @Operation(
    summary = "Notificar cliente",
    description = "Notificar cliente sobre a emissão do cartão.",
    tags = {"Notificar emissão do cartão"} 
    )
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Notificação realizada com sucesso"),
    @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping("/notificar")
    public ResponseEntity<ResponseDto> emitirNotificacao(@RequestBody RequestDto requestDto) {

        try{
                
         // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO DE VALIDAÇÃO DE INPUT :" + verificar.keySet());
            

            
            ResponseDto dto = notificacaoService.notificarCliente(requestDto);
       
            if(dto != null){
                return ResponseEntity.ok(dto);
            }
          //  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERRO NO PROCESSAMENTO DOS DADOS ");
      
        }catch(Exception e){

         //   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("ERRO NO SERVIDOR A PROCESSAR ESSA REQUISIÇÃO.");
        }

        return  null;
    }

}


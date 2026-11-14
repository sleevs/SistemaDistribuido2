package br.com.jsncartoesms.service.proposta;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.jsncartoesms.dto.PropostaCartaoDto;
import br.com.jsncartoesms.dto.PropostaResponseDto;
import br.com.jsncartoesms.entity.OutboxEntity;
import br.com.jsncartoesms.entity.PropostaCartaoEntity;
import br.com.jsncartoesms.repository.OutboxRepository;
import br.com.jsncartoesms.repository.PropostaCartaoRepository;
import br.com.jsncartoesms.util.OutboxStatus;
import br.com.jsncartoesms.util.PropostaStatus;
import jakarta.transaction.Transactional;

@Service
public class PropostaCartaoService {


    @Autowired
    private PropostaCartaoProducer CartaoProducer;

    @Autowired
    private PropostaCartaoRepository propostaRepository;

    @Autowired 
    private OutboxRepository outboxRepository;
    @Autowired 
    private ObjectMapper objectMapper;
    /*
     * criar cartao de credito 
     * registrar no banco de dados
     * realizar chamada para o servicço emissao de cartão
     * 
     */
    @Transactional 
    public PropostaResponseDto criarProposta(PropostaCartaoDto proposta) throws JsonProcessingException{
       
        if(proposta.getLimite().intValue() <= 1000){
           

          PropostaCartaoEntity novaPropostaEntity =  propostaRepository.save(converterDtoToEntity(proposta));
          PropostaResponseDto propostaResponseDto = converterEntityToDto(novaPropostaEntity);
          
          OutboxEntity outboxEntity = new OutboxEntity();
          outboxEntity.setId(UUID.randomUUID());
          outboxEntity.setAgregador(propostaResponseDto.getNumero());
          outboxEntity.setTipoEvento("CRIACAO DE PROPOSTA");
          outboxEntity.setTopico("proposta-criada");
          outboxEntity.setPayload(objectMapper.writeValueAsString(propostaResponseDto));
          outboxEntity.setStatus(OutboxStatus.PENDENTE);
          outboxEntity.setTentativa(0);
          outboxEntity.setCriadoEm(OffsetDateTime.now());
          outboxEntity.setProximaTentativaEm(OffsetDateTime.now());

          outboxRepository.save(outboxEntity);

         // CartaoProducer.enviarCartaoParaEmissao(propostaResponseDto);
            return propostaResponseDto;
        }
        return null;
    }


    

    private PropostaResponseDto converterEntityToDto(PropostaCartaoEntity entity){
         PropostaResponseDto novaPropostaDto = new PropostaResponseDto();
         novaPropostaDto.setBanco(entity.getBanco());
         novaPropostaDto.setEmail(entity.getEmail());
         novaPropostaDto.setLimite(entity.getLimite());
         novaPropostaDto.setNome(entity.getNome());
         novaPropostaDto.setTipo(entity.getTipo());
         novaPropostaDto.setStatus(PropostaStatus.VERIFICANDO.toString());
         novaPropostaDto.setData("REGISTRO" + Calendar.DATE);
         novaPropostaDto.setNumero(entity.getId().toString());
         return novaPropostaDto;
    }

    
    private  PropostaCartaoEntity converterDtoToEntity(PropostaCartaoDto dto){
        
         PropostaCartaoEntity novaProposta = new PropostaCartaoEntity();
            novaProposta.setId(UUID.randomUUID());
            novaProposta.setBanco(dto.getBanco());
            novaProposta.setEmail(dto.getEmail());
            novaProposta.setLimite(dto.getLimite());
            novaProposta.setNome(dto.getNome());
            novaProposta.setTipo(dto.getTipo());


            return novaProposta;
    }
}

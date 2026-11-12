package br.com.jsncartoesms.entity;



import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.OffsetDateTime;
import java.util.UUID;
import br.com.jsncartoesms.util.OutboxStatus;
import jakarta.persistence.Column;

@Table(name = "outbox")
@Entity
public class OutboxEntity{

    @Id 
    private UUID id;

    @Column(name = "agregador_id" ,nullable = false)
    private String agregador;

    @Column(name = "tipo_evento" ,nullable = false)
    private String tipoEvento;

    
    @Column(name = "topico" , nullable = false)
    private String topico;

    @Column(columnDefinition = "jsonb", nullable = false)
    private String payload;

    @Enumerated(EnumType.STRING)
    @Column(name = "status" ,nullable = false)
    private OutboxStatus status;

    @Column(name = "tentativa" )
    private Integer tentativa;

    @Column(name = "proxima_tentativa_em" )
    private OffsetDateTime proximaTentativaEm;

    @Column(name = "criado_em" )
    private OffsetDateTime criadoEm;

    @Column(name = "publicado_em" )
    private OffsetDateTime publicarEm;

    @Column(name = "ultimo_erro" )
    private String ultimoErro;

    @Column(name = "bloqueado_ate" )
    private OffsetDateTime bloqueadoAte;


    public OutboxEntity(){

    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getAgregador() {
        return agregador;
    }


    public void setAgregador(String agregador) {
        this.agregador = agregador;
    }


    public String getTipoEvento() {
        return tipoEvento;
    }


    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }


    public String getTopico() {
        return topico;
    }


    public void setTopico(String topico) {
        this.topico = topico;
    }


    public String getPayload() {
        return payload;
    }


    public void setPayload(String payload) {
        this.payload = payload;
    }


    public OutboxStatus getStatus() {
        return status;
    }


    public void setStatus(OutboxStatus status) {
        this.status = status;
    }


    public Integer getTentativa() {
        return tentativa;
    }


    public void setTentativa(Integer tentativa) {
        this.tentativa = tentativa;
    }


    public OffsetDateTime getProximaTentativaEm() {
        return proximaTentativaEm;
    }


    public void setProximaTentativaEm(OffsetDateTime proximaTentativaEm) {
        this.proximaTentativaEm = proximaTentativaEm;
    }


    public OffsetDateTime getCriadoEm() {
        return criadoEm;
    }


    public void setCriadoEm(OffsetDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    


    public String getUltimoErro() {
        return ultimoErro;
    }


    public void setUltimoErro(String ultimoErro) {
        this.ultimoErro = ultimoErro;
    }


    public OffsetDateTime getBloqueadoAte() {
        return bloqueadoAte;
    }


    public void setBloqueadoAte(OffsetDateTime bloqueadoAte) {
        this.bloqueadoAte = bloqueadoAte;
    }


    public OffsetDateTime getPublicarEm() {
        return publicarEm;
    }


    public void setPublicarEm(OffsetDateTime publicarEm) {
        this.publicarEm = publicarEm;
    }


    

}
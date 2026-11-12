package br.com.jsncartoesms.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.jsncartoesms.entity.OutboxEntity;

@Repository 
public interface OutboxRepository extends JpaRepository<OutboxEntity,UUID>{


    @Query(value = """
        SELECT * FROM outbox WHERE
            (status = 'PENDENTE' AND proxima_tentativa_em <= NOW() )
            OR (status = 'PROCESSANDO' AND bloqueado_ate < NOW())
        ORDER BY criado_em
        LIMIT :workerSize
        FOR UPDATE SKIP LOCKED
        """, nativeQuery = true)
    List<OutboxEntity> buscarEventosDisponiveis(@Param ("workerSize") int workerSize );

@Modifying
@Transactional
@Query(value = """
    UPDATE outbox e
    SET e.status = 'PUBLICAR',
        e.publicar_em = :publicarEm,
        e.bloqueado_ate = null,
        e.ultimo_erro = null
    WHERE e.id = :id
      AND e.status = 'PROCESSANDO'
""", nativeQuery= true)
public int marcarPublicacao(@Param("id") UUID id, @Param("publicarEm") OffsetDateTime publicarEm);
    
}
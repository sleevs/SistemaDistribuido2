package br.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.entity.NotificacaoEntity;

public interface NotificacaoRepository extends JpaRepository<NotificacaoEntity, Long>{
    
}

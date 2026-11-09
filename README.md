# Sistema de Emissão de Cartões de Crédito com Arquitetura Baseada em Microservices

Sistema backend distribuído que simula o fluxo de análise de crédito e emissão de cartões, utilizando arquitetura orientada a eventos com Apache Kafka e Spring Boot.

O sistema modela o processo de um banco desde o cadastro do cliente até a emissão do cartão.

---

## Problema

Processos de emissão de cartão de crédito em sistemas bancários tradicionais costumam ser:
- Fortemente acoplados
- Difíceis de escalar
- Dependentes de fluxos síncronos
- Pouco resilientes a falhas

Isso dificulta a evolução do sistema e a integração entre etapas como análise de crédito e emissão de cartão.

---

## Solução

Este projeto implementa uma arquitetura baseada em microservices e comunicação assíncrona para simular o fluxo de emissão de cartão.

O sistema é dividido em serviços independentes que se comunicam via eventos.

---

## Funcionalidades

- Cadastro de cliente e proposta de crédito (`cartoes-ms`)
- Processamento e emissão de cartão (`emissor-ms`)
- Comunicação assíncrona via Apache Kafka
- Simulação de envio de cartão via e-mail (template)
- Arquitetura desacoplada baseada em eventos


---


## Responsabilidade de cada serviço

- cartoes-ms: Cadastra o cliente, recebe a proposta inicial e publica o evento no tópico `pedido-criado`.

- emissor-ms: Consome a proposta do tópico `pedido-criado`, simula a análise/emissão do cartão e publica o evento no tópico `cartao-emitido`.

- notificacao-ms: Consome o evento do tópico `cartao-emitido` e simula o envio de e-mail ao cliente.


 - Política de Retry: O consumidor tentará processar a mensagem **3 vezes** em caso de falhas intermitentes.
 - Encaminhamento para DLT:** Caso o erro persista após as 3 tentativas, a mensagem é movida para o respectivo tópico de falha:
 - Falha na Emissão (`emissor-ms`): Encaminhada para `pedido-criado-dead-letter`.
 - Falha na Notificação (`notificacao-ms`): Encaminhada para `cartao-emitido-dead-letter`.


---


## Stack

- Java 21
- Spring Boot
- Apache Kafka
- Docker
- Maven

---

## Arquitetura

```text
Client
  ↓
cartoes-ms (Producer)
  ↓
Kafka Topic (cartao-topic)
  ↓
emissor-ms (Consumer)
  ↓
Email Notification (simulação de emissão do cartão)

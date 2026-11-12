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

- Cadastro de clientes e propostas de crédito (`cartoes-ms`)
- Processamento e emissão de cartões (`emissor-ms`)
- Comunicação assíncrona entre microservices via Apache Kafka
- Notificação de emissão de cartões por e-mail (`notification-ms`)
- Arquitetura desacoplada e orientada a eventos (Event-Driven Architecture)


---


## Responsabilidade de cada serviço

- cartoes-ms: Cadastra o cliente, recebe a proposta inicial e publica o evento no tópico `proposta-criada`.
<<<<<<< HEAD

- emissor-ms: Consome a proposta do tópico `proposta-criada`, simula a análise/emissão do cartão e publica o evento no tópico `cartao-emitido`.

- notificacao-ms: Consome o evento do tópico `cartao-emitido` e simula o envio de e-mail ao cliente.


 - Política de Retry: O consumidor tentará processar a mensagem **3 vezes** em caso de falhas intermitentes.
 - Encaminhamento para DLT:** Caso o erro persista após as 3 tentativas, a mensagem é movida para o respectivo tópico de falha:
 - Falha na Emissão (`emissor-ms`): Encaminhada para `pedido-criado-dead-letter`.
 - Falha na Notificação (`notificacao-ms`): Encaminhada para `cartao-emitido-dead-letter`.

=======
- emissor-ms: Consome a proposta do tópico `proposta-criada`, simula a análise/emissão do cartão e publica o evento no tópico `cartao-emitido`.
- notificacao-ms: Consome o evento do tópico `cartao-emitido` e simula o envio de e-mail ao cliente.


##### Tópicos Kafka

- `proposta-criada`: Recebe eventos de propostas de emissão de cartões publicados pelo cartoes-ms.
- `cartao-emitido`: Comunica a conclusão da emissão do cartão pelo emissor-ms.
- `proposta-criada-dead-letter`: Armazena mensagens que falharam no processamento pelo emissor-ms, após esgotar as tentativas de retry.
- `cartao-emitido-dead-letter`: Armazena mensagens que falharam no processamento pelo notification-ms, após esgotar as tentativas de retry.

##### Consumer Groups

- `emissor-group` (`emissor-ms`): Consome proposta e realiza a emissão dos cartões.
- `notification-group` (`notification-ms`): Consome eventos de emissão e envia notificações por e-mail.


##### Estratégia de Resiliência: Dead Letter Topic
 - Política de Retry: O consumidor tentará processar a mensagem **3 vezes** em caso de falhas intermitentes.
 - Encaminhamento para DLT: Caso o erro persista após as 3 tentativas, a mensagem é movida para o respectivo tópico de falha:
 - Falha na Emissão (`emissor-ms`): Encaminhada para `proposta-criado-dead-letter`.
 - Falha na Notificação (`notificacao-ms`): Encaminhada para `cartao-emitido-dead-letter`.

##### Estratégias de Resiliência e Consistência: Outbox Pattern.

Consistência na Publicação: 
Transactional Outbox Pattern Para evitar o cenário de falha atômica na criação de propostas no serviço `cartoes-ms` (onde os dados são salvos no banco, mas a publicação no Kafka falha por instabilidade do broker, ou vice-versa):

Persistência Atômica: 
A gravação dos dados da proposta e a intenção do evento de publicação (PropostaCriadoEvent) são executadas dentro da mesma transação ACID do banco de dados relacional.

Garantia de Entrega : 
Um processo em segundo plano monitora a tabela de outbox e garante que a mensagem seja enviada e confirmada pelo Apache Kafka com sucesso antes de marcá-la como processada, eliminando cenários de inconsistência entre o banco e o cluster.

- cartoes-ms: salva a proposta e o evento na Outbox.
- emissor-ms: consome o evento proposta , processa a emissão e salva o cartão e o evento na Outbox.
- notification-ms: consome o evento, registra a notificação na Outbox e realiza o envio do e-mail.

---

## Arquitetura e Decisões Técnicas

- Apache Kafka para comunicação assíncrona entre microservices.
- Um tópico para cada evento de negócio.
- Um consumer group independente por serviço consumidor.
- Retry + Dead Letter Topic (DLT) para tratamento de falhas.
- `pedidoId` como chave das mensagens para garantir ordenação por pedido.
- Idempotência para evitar processamento e emissão duplicados.
- Transactional Outbox Garantia de Entrega com Transactional Outbox

### Fluxo da Arquitetura

![Arquitetura do Projeto](docs/images/fluxo_da_arquitetura.png)

## Stack

- Java 21
- Maven
- Spring Boot
- Apache Kafka
- Docker
- Terraform
- Postgres
- OpenTelemetry

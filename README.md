# Sistema de Emissão de Cartões de Crédito com Arquitetura Baseada em Microservices

Sistema backend distribuído que simula o fluxo de análise de crédito e emissão de cartões, utilizando arquitetura orientada a eventos com Apache Kafka e Spring Boot.

O sistema modela o processo de um banco desde o cadastro do cliente até a emissão do cartão.

---

## Visão Geral
Sistema distribuído de emissão de cartões de crédito, desenvolvido com Java 21 e Spring Boot, utilizando arquitetura de microsserviços e comunicação assíncrona baseada em eventos com Apache Kafka.

O projeto simula um fluxo bancário de ponta a ponta, desde o cadastro do cliente e submissão da proposta de crédito até o processamento, emissão do cartão e notificação.

A arquitetura prioriza desacoplamento, escalabilidade, resiliência e consistência de dados, implementando padrões como Transactional Outbox, Idempotência, Retry e Dead Letter Topics (DLT).

O objetivo é demonstrar a aplicação de conceitos de engenharia de software e arquitetura de sistemas distribuídos em um cenário próximo ao de uma plataforma bancária real.

---

### Fluxo da Arquitetura

![Arquitetura do Projeto](docs/imagens/fluxo_da_arquitetura.png)

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

## Stack

- Java 21
- Maven
- Spring Boot
- Apache Kafka
- Docker
- Terraform
- Postgres
- OpenTelemetry

---




## Funcionalidades

- Cadastro de clientes e propostas de crédito (`cartoes-ms`)
- Processamento e emissão de cartões (`emissor-ms`)
- Comunicação assíncrona entre microservices via Apache Kafka
- Notificação de emissão de cartões por e-mail (`notificacao-ms`)
- Arquitetura desacoplada e orientada a eventos (Event-Driven Architecture)


---


## Responsabilidade de cada serviço

- cartoes-ms: Cadastra o cliente, recebe a proposta inicial e publica o evento no tópico `proposta-criada`.
- emissor-ms: Consome a proposta do tópico `proposta-criada`, simula a análise/emissão do cartão e publica o evento no tópico `cartao-emitido`.
- notificacao-ms: Consome o evento do tópico `cartao-emitido` e simula o envio de e-mail ao cliente.


---



## Como executar: 


### 1. Provisionamento da Infraestrutura com Terraform
Terraform Provisiona os três nós Kafka. Acesse o diretório de infraestrutura e execute:

```bash
terraform init
terraform validate
terraform plan
terraform apply
```

### 2. Inicialização dos Bancos de Dados
Docker Compose inicializa os três bancos PostgreSQL e inicializa um OpenTelemetry Collector compartilhado
```bash
docker compose up -d
```

### 3. Build dos Microservices
Execute o build dos serviços na seguinte ordem:

1. **cartoes-ms**
2. **emissor-ms**
3. **notificacao-ms**

Em cada diretório, execute:

```bash
 mvn clean install
```

### 4. Inicialização dos Microservices
Inicie os serviços na seguinte ordem:

1. **cartoes-ms**
2. **emissor-ms**
3. **notificacao-ms**

Em cada diretório, execute:


```bash
mvn spring-boot:run

```

---


##### Tópicos Kafka

- `proposta-criada`: Recebe eventos de propostas de emissão de cartões publicados pelo cartoes-ms.
- `cartao-emitido`: Comunica a conclusão da emissão do cartão pelo emissor-ms.
- `proposta-criada-dead-letter`: Armazena mensagens que falharam no processamento pelo emissor-ms, após esgotar as tentativas de retry.
- `cartao-emitido-dead-letter`: Armazena mensagens que falharam no processamento pelo notificatao-ms, após esgotar as tentativas de retry.

---


##### Consumer Groups

- `emissor-group` (`emissor-ms`): Consome proposta e realiza a emissão dos cartões.
- `notificacao-group` (`notificacao-ms`): Consome eventos de emissão e envia notificações por e-mail.

---



##### Estratégia de Resiliência: Dead Letter Topic

 - Política de Retry: O consumidor realizar até 3 tentativas de processamento em caso de falhas.
 - Encaminhamento para DLT: Após esgotar as tentativas, a mensagem é encaminhada para o tópico de falha correspondente.
 - Falha na Emissão (`emissor-ms`): Encaminhada para `proposta-criada-dead-letter` .
 - Falha na Notificação (`notificacao-ms`): Encaminhada para `cartao-emitido-dead-letter`.

---


##### Estratégias de Resiliência e Consistência: Outbox Pattern.

- Transactional Outbox: 
Utilizado para garantir consistência entre a persistência dos dados no banco relacional e a publicação de eventos no Kafka.

- cartoes-ms: salva a proposta e o evento na Outbox.
- emissor-ms: consome o evento proposta , processa a emissão e salva o cartão e o evento na Outbox.
- notificacao-ms: consome o evento, registra a notificação na Outbox e realiza o envio do e-mail.

Um processo em segundo plano publica os eventos pendentes da Outbox no Kafka, reduzindo o risco de inconsistência entre banco de dados e mensageria.


---

## Arquitetura e Decisões Técnicas

- Apache Kafka para comunicação assíncrona entre microservices.
- Um tópico para cada evento de negócio.
- Um consumer group independente por serviço consumidor.
- Retry + Dead Letter Topic (DLT) para tratamento de falhas.
- `propostaId` como chave das mensagens para garantir ordenação por proposta.
- Idempotência para evitar processamento e emissão duplicados.
- Transactional Outbox Garantia de Entrega com Transactional Outbox


---


## Infraestrutura: 

---

### Oranização do Terraform
- O Terraform é utilizado para provisionar e gerenciar a infraestrutura do cluster Apache Kafka usando Docker como Provier.
- A infraestrutura é declarada em arquivos .tf, automatizando  a criação e a configuração dos containers Kafka.
- O provisionamento dos containers Kafka é realizado por meio do Docker Provider do Terraform, utilizando conexões SSH para acessar remotamente os Docker Engines dos três computadores.

```bash
kafka-cluster/
  /providers.tf
  /variables.tf
  /terraform.tfvars
  /main.tf
  /outputs.tf
```
- providers.tf : Configuração do provider Docker
- variables.tf : Declaração das variáveis
- terraform.tfvars : Definição dos valores das variáveis
- main.tf : Definição dos recursos e containers Kafka
- outputs.tf : Exposição das informações dos recursos provisionados


### Arquitetura do cluster Kafka

O cluster utiliza Apache Kafka com arquitetura Kafka Raft Metadata mode.
São usados três nós distribuídos em computadores diferentes, cada um executando um container Kafka independente.

#### Gerenciamento de liderança e tolerância a falhas
O cluster utiliza o mecanismo de consenso Raft para gerenciamento e replicação dos metadados..
A configuração utiliza o quorum estático, definido pela propriedade controller.quorum.voters.
- Um Controller Leader é eleito entre os 3 controllers.
- Os outros 2 controllers mantêm réplicas dos metadados.
- O cluster suporta a indisponibilidade de um computador sem perder o quorum.
- Os brokers distribuem a liderança das partições entre os nós.
- O cluster possui 3 controllers votantes, são necessários 2 para manter o quorum.

Os tópicos Kafka são configurados com Replication Factor 3 e min.insync.replicas=2.

```bash
kafka-topics.sh --bootstrap-server localhost:9092 \
  --create --topic proposta-criada \
  --partitions 3 --replication-factor 3 --config min.insync.replicas=2
```



#### Configurações utilizadas:
- 3 partições: permite distribuir o processamento entre consumidores.
- Replication Factor 3: cada partição possui três réplicas distribuídas entre os brokers.
- min.insync.replicas=2 : permite manter réplicas dos dados caso um broker fique indisponível.




#### Comportamento diante de falhas

- 3 brokers disponíveis : Escritas permitidas.
- 1 broker indisponível : Escritas continuam com 2 réplicas sincronizadas.
- 2 brokers indisponíveis : Escritas recusadas.
- 1 controller indisponível : Quorum KRaft permanece disponível.



#### Confiabilidade e garantia de entrega dos Producers

Os microsserviços que publicam eventos no Kafka utilizam configurações de confiabilidade para garantir maior segurança na publicação das mensagens.

Configurações utilizadas:

```bash
properties
spring.kafka.producer.acks=all
spring.kafka.producer.properties.enable.idempotence=true
spring.kafka.producer.properties.max.in.flight.requests.per.connection=5
spring.kafka.producer.properties.delivery.timeout.ms=120000
spring.kafka.producer.properties.request.timeout.ms=30000
```


#### Responsabilidade das configurações:

- acks=all: aguarda confirmação de todas as réplicas sincronizadas (ISR).

- enable.idempotence=true: evita duplicação de mensagens provocada por retries do Producer.

- max.in.flight.requests.per.connection=5: permite até cinco requisições simultâneas, mantendo a ordenação com idempotência habilitada.

- delivery.timeout.ms=120000: define o tempo máximo para entrega da mensagem.

- request.timeout.ms=30000: define o tempo máximo de espera pela resposta de uma requisição.


  
####  Persistência de dados
- Cada computador mantém seu próprio volume para armazenar os dados do Kafka.
- Isso permite que os dados permaneçam disponíveis após a reinicialização dos containers, sem depender do armazenamento de outro computador.
- A replicação entre brokers contribui para a proteção contra falhas de infraestrutura.

---

### Observabilidade: 
demonstrar como o OpenTelemetry é utilizado. --- DOING

---

### Testes: 
apresentar testes unitários e de integração, se implementados. --- DOING


---



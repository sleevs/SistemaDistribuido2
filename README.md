
Demostração de um sistema que resolve um problema de cartão de crédito onde um
dado banco trabalha com cartões de crédito e 
determinado Cliente precise emitir um cartão. Então, o sistema deverá lançar proposta 
de crédito, limite e emitir cartão. 


### Comportamento

Microservice cartoes-ms responsável por cadastrar o cliente e a proposta de crédito.

Microservice emissor-ms responsável por emitir o cartão físico (Para exemplificar realize uma notificação por E-mail com um template do cartão tendo todas as informações necessárias). 




### Executar a aplicação backend 

Pré-requisitos

 -  Java 21
 -  Maven
 -  Apache Kafka
 -  Docker

### Executar o seguinte comando docker para iniciar o Apache Kafka :

docker-compose up -d

##### comandos para criar o topico no o Apache Kafka :

docker container exec -it kafka /bin/bash

kafka-console-producer --broker-list localhost:9092 --topic cartao-topic  

### Comando maven para iniciar o microserviço :

mvn clean install

mvn spring-boot:run

# PAGAMENTO API

Serviço responsável pelos o controle de fluxos de pagamento.
As principais funcionalidades são:

- Checkout de pedido
- Webhook de alteração de status de pagamento

## Sequencia Saga

```mermaid
sequenceDiagram
    autonumber
    actor totem
    totem ->> Pagamento API: solicita checkout
    Pagamento API ->> DB Pagamento: persiste objeto pagamento
    DB Pagamento -->> Pagamento API: devolve objeto
    
    Pagamento API -->> totem: recebe o id do pagamento
    note over Pagamento API: aqui inicia a SAGA de criação do pedido

    Pagamento API ->> MQ: envia mensagem no topico novo-pedido<br>{"pagamentoId": 1, checkout: {}}
    MQ ->> Pedido API: consumer recebe a criação do pedido
    Pedido API ->> DB Pedido: persiste o novo pedido
    DB Pedido -->> Pedido API: devolve o objeto
    
    Pedido API -->> MQ: envia topico pedido-criado<br> com o id do pedido<br>{"pagamentoId": 1, pedidoId: 1}
    MQ -->> Pagamento API: consome topico pedido-criado
    
    Pagamento API ->> DB Pagamento: atualiza o pagamento<br> com o id do pedido

    Pagamento API ->> Meio Pagamento: processa pagamento
    note over Meio Pagamento: processo ficticio

    note over Pagamento API: aqui inicia a SAGA de recebimento do pagamento
    Meio Pagamento -->> Pagamento API: devolve no webhook
    Pagamento API ->> DB Pagamento: atualiza status do pagamento
    DB Pagamento -->> Pagamento API: recebe o objeto
    
    Pagamento API ->> MQ: publica no topico pagamento-pedido-pago<br>{"pedidoId": 1}
    MQ -->> Pedido API: consumer recebe atualização
    
    Pedido API ->> DB Pedido: atualiza status do pedido<br>cozinha pode preparar
```

** Novos metodos

- API para devolver o status do pagamento via id do pagamento
- Caso a criação do pedido de erro, armazenar a mensagem de erro da fila 

## Diagrama Arquitetural da comunicação entre os serviços

![diagrama](tc-s1-32-entrega4-v6.drawio.png)

## Definição dos pacotes

Os pacotes seguem uma estrutura simples de separação exemplificada a seguir:

- [clients](src%2Fmain%2Fjava%2Fbr%2Fcom%2Ffiap%2Fsoat1%2Ft32%2Fclients)

- [controllers](src%2Fmain%2Fjava%2Fbr%2Fcom%2Ffiap%2Fsoat1%2Ft32%2Fcontrollers)

- [enums](src%2Fmain%2Fjava%2Fbr%2Fcom%2Ffiap%2Fsoat1%2Ft32%2Fenums)

- [exceptions](src%2Fmain%2Fjava%2Fbr%2Fcom%2Ffiap%2Fsoat1%2Ft32%2Fexceptions)

- [models](src%2Fmain%2Fjava%2Fbr%2Fcom%2Ffiap%2Fsoat1%2Ft32%2Fmodels)

- [repositories](src%2Fmain%2Fjava%2Fbr%2Fcom%2Ffiap%2Fsoat1%2Ft32%2Frepositories)

- [services](src%2Fmain%2Fjava%2Fbr%2Fcom%2Ffiap%2Fsoat1%2Ft32%2Fservices)

- [mappers](src%2Fmain%2Fjava%2Fbr%2Fcom%2Ffiap%2Fsoat1%2Ft32%2Futils%2Fmappers)

## Definições de testes

Para os testes de unidade, foram considerados os pacotes <b>controllers</b> e <b>services</b>.

Para services, utilizamos o Mockito para mockar toda a camada repository e validar as regras de negócio isoladamente.

Nas controllers, fizemos o mock das services e validamos contratos das APIs.

Para calcular a cobertura de teste do projeto utilizando o Jacoco, basta executar o comando `mvn clean install` e acessar o resultado do cálculo na pasta target/site/jacoco/index.html.
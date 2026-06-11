[Read this page in English](README.md)

# Uai-Foods — Sistema de Pedidos

Uma aplicação de pedidos via console desenvolvida em Java, projetada para simular um fluxo real de pedidos: cadastro do cliente, navegação pelo cardápio, seleção de itens, processamento de pagamento e persistência do pedido.

---

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 25+ |
| JDK | OpenJDK 25+ |

Nenhuma dependência externa ou ferramenta de build é necessária. O projeto compila e executa com o toolchain padrão do Java.

---

## Finalidade

O Uai-Foods foi desenvolvido como projeto de aprendizado para praticar princípios de design orientado a objetos em Java. O foco foi na separação de responsabilidades entre camadas, modelagem de um domínio real com entidades e serviços, tratamento de exceções específicas do domínio e manutenção do ponto de entrada da aplicação limpo e minimal.

---

## Funcionalidades

- Cadastro de cliente com validação de nome e CPF
- Exibição interativa do cardápio no console
- Pedido de um ou múltiplos itens em uma única sessão
- Geração de resumo do pedido com subtotais por item e valor total
- Processamento de pagamento com suporte a múltiplos métodos
- Cálculo e retorno do troco ao cliente
- Persistência do pedido em arquivo no caminho informado pelo usuário
- Tratamento de exceções de domínio com mensagens de erro descritivas

---

## Estrutura do Projeto

```
src/
├── application/
│   ├── Main.java
│   └── ConsoleUI.java
└── model/
    ├── entities/
    │   ├── Client.java
    │   ├── Order.java
    │   ├── OrderItem.java
    │   ├── Product.java
    │   └── enums/
    │       └── OrderStatus.java
    ├── exceptions/
    │   └── DomainException.java
    ├── service/
    │   ├── Menu.java
    │   ├── OrderRepository.java
    │   ├── OrderService.java
    │   └── PaymentService.java
    └── util/
        ├── CheckOrderNumber.java
        ├── DateTimeUtil.java
        ├── PaymentUtil.java
        └── SimpleValidation.java
```

---

## Responsabilidades das Camadas

### `application`
Ponto de entrada da aplicação. `Main` inicializa o locale de runtime e delega toda a execução para `ConsoleUI`, que gerencia o fluxo completo de interação com o usuário — cadastro, pedido, pagamento e salvamento em arquivo.

### `model.entities`
Contém os objetos de domínio centrais da aplicação.

- `Client` — representa o cliente, armazenando nome e CPF com validação em ambos os campos
- `Product` — representa um item do cardápio com nome e preço
- `OrderItem` — associa um `Product` a uma quantidade e calcula o subtotal da linha
- `Order` — agrega instâncias de `OrderItem`, rastreia o status, calcula o total, gera o resumo do pedido e processa o pagamento
- `enums/OrderStatus` — define os estados do ciclo de vida de um pedido: `PENDING` e `FINISHED`

### `model.exceptions`
- `DomainException` — exceção customizada não verificada utilizada para sinalizar violações de regras dentro da camada de domínio, como CPF de tamanho inválido, campos nulos ou números de item inválidos

### `model.service`
Contém os serviços da aplicação que coordenam a lógica de domínio.

- `Menu` — mantém o catálogo de produtos e o expõe para exibição e seleção
- `OrderService` — responsável por instanciar objetos `Order` e delegar o processamento de pagamento
- `OrderRepository` — gerencia a persistência do pedido, gravando o resumo em arquivo
- `PaymentService` — interface que define o contrato para implementações de processamento de pagamento

### `model.util`
Classes utilitárias com métodos auxiliares sem estado.

- `CheckOrderNumber` — valida se um número de item do cardápio está dentro do intervalo aceito
- `DateTimeUtil` — fornece um formatador de data/hora compartilhado utilizado nos resumos de pedido
- `PaymentUtil` — constrói a mensagem de seleção do método de pagamento e retorna a implementação adequada de `PaymentService` com base na escolha do usuário
- `SimpleValidation` — valida entradas do usuário para confirmações sim/não e escolhas de método de pagamento

---

## Como Executar

```bash
# Compilar
javac -d out -sourcepath src src/application/Main.java

# Executar
java -cp out application.Main
```

---

## Fluxo do Pedido

```
Início
  └── Cadastro do cliente (nome + CPF)
        └── Exibição do cardápio
              └── Seleção de itens (repetível)
                    └── Resumo do pedido
                          └── Seleção do método de pagamento
                                └── Informação do valor
                                      └── Pedido salvo em arquivo
```

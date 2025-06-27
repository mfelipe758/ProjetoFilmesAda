# Projeto Filmes Ada

Este é um sistema simples para cadastro e gerenciamento de filmes e notícias sobre filmes. O projeto foi desenvolvido em Java usando Spring Boot, com persistência dos dados em arquivos JSON.

## Funcionalidades

* Cadastro, edição e remoção de filmes
* Cadastro, edição e remoção de notícias
* Listagem de filmes e notícias
* Favoritar filmes
* Interface web simples usando páginas HTML

## Como rodar o projeto

### 1. Pré-requisitos:

* Java 11 ou superior
* Maven

### 2. Passos para rodar:

* Clone o repositório
* No terminal, navegue até a pasta do projeto
* Execute o comando:
    ```bash
    ./mvnw spring-boot:run
    ```
* Acesse o sistema pelo navegador em: [http://localhost:8080](http://localhost:8080)

## Estrutura dos dados

Os dados de filmes e notícias são salvos em arquivos JSON na pasta `src/main/resources/json`.

## Observações

Este projeto é apenas para fins de estudo e demonstração.
Fique à vontade para modificar e testar!

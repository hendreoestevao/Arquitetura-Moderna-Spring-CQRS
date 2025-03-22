# 📚 Guia de Aprendizado: CQRS, RabbitMQ, Spring e Docker

## 📌 O que eu aprendi neste curso

### 🏗 Arquitetura CQRS (Command Query Responsibility Segregation)
- Conceitos sobre arquitetura CQRS.
- Implementação de CQRS em projetos Spring.
- Vantagens e desvantagens do uso de CQRS.
- Quando usar e quando não usar CQRS.

### 🐇 RabbitMQ e Mensageria
- Conceitos básicos de RabbitMQ.
- Tipos de filas e exchanges (direta, tópica, fanout).
- Configuração de bindings e routing keys.
- Integração do RabbitMQ com Spring.

### 🌱 Introdução ao Spring Framework
- Principais componentes do Spring.
- Anotações essenciais (@Component, @Autowired, @Service, @RestController, etc.).
- Inversão de controle (IoC) e injeção de dependências (DI).
- Principais pacotes do Spring (Spring Boot, Spring Data JPA, Spring AMQP, etc.).

### 🌐 Construção de APIs RESTful
- Princípios e boas práticas para construção de APIs RESTful.
- Uso de ferramentas como Swagger e Postman para documentação e testes.
- Convenções de nomenclatura, versões de API, códigos de status HTTP e payloads.

### 🐳 Containers e Docker
- Conceitos básicos de containers e imagens Docker.
- Uso do Docker Compose para orquestração de múltiplos containers.
- Configuração de volumes, redes e portas em Docker.
- Comandos principais do Docker para gerenciamento de containers.

### 🗄️ Bancos de Dados: PostgreSQL e MongoDB
- Configuração e uso de bancos de dados PostgreSQL e MongoDB.
- Replicação de dados entre bancos de dados usando microservices.

## 🚀 Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**
- **RabbitMQ**
- **Docker & Docker Compose**
- **PostgreSQL & MongoDB**
- **Swagger & Postman**

## 📜 Pré-requisitos
Antes de começar, certifique-se de ter os seguintes itens instalados em seu ambiente de desenvolvimento:
- **Java 17+**
- **Maven 3.8+**
- **Docker e Docker Compose**
- **RabbitMQ**
- **PostgreSQL & MongoDB**
- **Postman (opcional)**

## ⚡ Como executar o projeto
1. Clone este repositório:
   ```sh
   git clone https://github.com/hendreoestevao/API-com-Arquitetura-Moderna-Spring-CQRS.git
   cd seu-repositorio
   ```
2. Inicie os containers necessários com Docker Compose:
   ```sh
   docker-compose up -d
   ```
3. Execute o projeto com Maven:
   ```sh
   mvn spring-boot:run
   ```
4. Acesse a API via Swagger UI:
   ```sh
   http://localhost:8080/swagger-ui/
   ```

## 🤝 Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.

## 📝 Licença
Este projeto está sob a licença MIT. Sinta-se livre para usá-lo e modificá-lo conforme necessário.

---
Desenvolvido com ❤️ por Hendreo Estevão!
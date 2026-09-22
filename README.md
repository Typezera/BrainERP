# 🧠 BrainERP

> REST API desenvolvida em Java e Spring Boot para gerenciamento de usuários, empresas, produtos e pedidos, com autenticação JWT, autorização baseada no usuário autenticado e arquitetura organizada com CQRS e Use Cases.

---

## 📌 Sobre o projeto

O **BrainERP** é uma API REST desenvolvida com o objetivo de aplicar, na prática, conceitos de desenvolvimento backend, arquitetura de software, segurança, persistência de dados e organização de código.

O projeto foi construído utilizando **Spring Boot** e possui diferentes domínios responsáveis por representar as principais operações de um sistema ERP simplificado.

Durante o desenvolvimento foram aplicados conceitos como:

- REST API
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- BCrypt
- DTOs
- Bean Validation
- CQRS
- Use Cases
- Autorização baseada no usuário autenticado
- Relacionamentos JPA
- Tratamento global de exceções
- OpenAPI / Swagger
- Docker
- Git/GitHub

O projeto foi desenvolvido com foco em **separação de responsabilidades, segurança e organização do código**, evitando concentrar toda a regra de negócio em Controllers ou Services genéricos.

---

# 🚀 Tecnologias

### Backend

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- OAuth2 Resource Server
- JWT
- BCrypt
- Bean Validation
- OpenAPI / Swagger

### Banco de dados

- MySQL

### Infraestrutura

- Docker
- Docker Compose

### Ferramentas

- Maven
- Git
- GitHub
- IntelliJ IDEA
- Postman
- Swagger UI
- DBeaver

#### Pré-requisitos
   - Git
   - Java 21
   - Maven
   - Docker
   - Docker Compose

#### Clonar
   git clone

#### Entrar na pasta
   cd Brain

####Gerar o JAR
   mvn clean package

#### Subir aplicação
   docker compose up -d --build

#### Acessar
   http://localhost:8080

---

# 🏗️ Arquitetura

O BrainERP utiliza uma organização baseada em **domínios**, combinada com os conceitos de **CQRS** e **Use Cases**.

A aplicação separa operações de leitura e escrita, evitando concentrar responsabilidades diferentes em uma única camada.

Uma visão simplificada:

```text
                    ┌─────────────────────┐
                    │       Client        │
                    │ Postman / Swagger   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Controllers     │
                    └──────────┬──────────┘
                               │
                    ┌──────────┴──────────┐
                    │                     │
                    ▼                     ▼
              ┌───────────┐       ┌─────────────┐
              │  Queries  │       │  Use Cases  │
              │   READ    │       │   WRITE     │
              └─────┬─────┘       └──────┬──────┘
                    │                    │
                    └─────────┬──────────┘
                              ▼
                    ┌─────────────────────┐
                    │    Repositories     │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       MySQL         │
                    └─────────────────────┘


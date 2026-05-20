# 📚 Biblioteca Anandamoyi API

API REST para gerenciamento de biblioteca desenvolvida com **Spring Boot**, utilizando **Clean Architecture**, autenticação **JWT** e controle de acesso por perfis (roles).

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- H2 Database
- Maven
- Clean Architecture

---

## 🏗 Arquitetura

O projeto foi estruturado seguindo princípios de **Clean Architecture**, com separação clara de responsabilidades:


# 📚 Biblioteca API — Anandamoyi

Sistema de gerenciamento de biblioteca desenvolvido com **Java + Spring Boot**, utilizando princípios de **Clean Architecture**, autenticação com **JWT**, controle de empréstimos, devoluções e multas automáticas.

Projeto desenvolvido para estudos, portfólio e aprimoramento profissional, inspirado nos ensinamentos do Mestre Ramatis e nos princípios de organização, disciplina e evolução contínua através do conhecimento.

---

# 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- H2 Database
- Maven
- JUnit 5
- MockMvc

---

# 🏗️ Arquitetura

O projeto foi desenvolvido utilizando os princípios da:

## ✅ Clean Architecture

Separando responsabilidades em camadas independentes:

```text
domain
application
infra
web
```

---

# 📂 Estrutura do Projeto

```text
src/main/java/br/com/biblioteca/anandamoyi

├── application
│   ├── dto
│   ├── usecase
│   └── usuario
│
├── domain
│   ├── entity
│   ├── enums
│   ├── exception
│   └── repository
│
├── infra
│   ├── persistence
│   ├── security
│   └── web
│
└── test
```

---

# 🔐 Funcionalidades

## ✅ Autenticação e Segurança

- Login com JWT
- Controle de acesso por Roles
- Rotas protegidas
- Filtro JWT customizado
- Spring Security configurado por perfil de acesso

---

## 👤 Usuários

- Cadastro de usuários
- Perfis:
    - ADMIN
    - BIBLIOTECARIO
    - LEITOR
- Validação de email
- Validação de senha
- Controle de usuários ativos

---

## 📚 Livros

- Cadastro de livros
- Cadastro de edições
- Controle de exemplares
- Disponibilidade automática
- Busca por ID
- Listagem completa

---

## 🔄 Empréstimos

- Empréstimo de exemplares
- Controle de disponibilidade
- Devolução de livros
- Controle de empréstimos ativos
- Controle de empréstimos devolvidos
- Controle de empréstimos atrasados
- Paginação
- Filtros dinâmicos

---

## 💰 Multas

- Cálculo automático de multa
- Controle de atraso
- Registro automático da data de devolução

---

# 🔑 Roles do Sistema

| Role | Permissões |
|------|-------------|
| ADMIN | Controle total do sistema |
| BIBLIOTECARIO | Gerenciamento da biblioteca |
| LEITOR | Consulta e empréstimos |

---

# 📡 Endpoints Principais

# 🔐 Auth

## Login

```http
POST /auth/login
```

### Body

```json
{
  "email": "admin@email.com",
  "senha": "123456"
}
```

---

# 👤 Usuários

## Criar usuário

```http
POST /usuarios
```

### Body

```json
{
  "nome": "Carlos",
  "email": "carlos@email.com",
  "senha": "123456",
  "role": "LEITOR",
  "ativo": true
}
```

---

## Listar usuários

```http
GET /usuarios
```

---

# 📚 Livros

## Criar livro

```http
POST /livros
```

### Body

```json
{
  "titulo": "Clean Code",
  "autor": "Robert C. Martin",
  "codigoBN": "BN001",
  "isbn": "9780132350884",
  "edicao": "1ª edição",
  "quantidadeExemplares": 2
}
```

---

## Buscar livro por ID

```http
GET /livros/{id}
```

---

## Listar livros

```http
GET /livros
```

---

# 🔄 Empréstimos

## Emprestar exemplar

```http
POST /livros/edicoes/{edicaoId}/emprestar?leitorId={id}
```

---

## Devolver exemplar

```http
PUT /emprestimos/{id}/devolver
```

---

## Listar empréstimos

```http
GET /emprestimos
```

---

## Filtrar empréstimos

### Ativos

```http
GET /emprestimos?status=ATIVO
```

### Devolvidos

```http
GET /emprestimos?status=DEVOLVIDO
```

### Atrasados

```http
GET /emprestimos?status=ATRASADO
```

---

# ✅ Validações

O sistema possui validações para:

- Campos obrigatórios
- Email inválido
- Senha mínima
- Quantidade inválida
- Role obrigatória
- Dados inconsistentes

---

# ⚠️ Tratamento de Erros

A aplicação possui um `GlobalExceptionHandler` responsável por tratar:

- 400 Bad Request
- 401 Unauthorized
- 403 Forbidden
- 404 Not Found
- Erros de validação
- Regras de negócio

---

# 🧪 Testes

O projeto possui:

## ✅ Testes Unitários
- UseCases
- Domain
- DTOs
- Mappers

## ✅ Testes de Controller
- MockMvc
- Endpoints REST

## ✅ Testes de Segurança
- JWT
- Roles
- 401 Unauthorized
- 403 Forbidden

---

# 🗄️ Banco de Dados

Atualmente o projeto utiliza:

## ✅ H2 Database

Console disponível em:

```text
http://localhost:8080/h2-console
```

---

# ▶️ Como Executar o Projeto

## Clonar repositório

```bash
git clone URL_DO_REPOSITORIO
```

---

## Entrar na pasta do projeto

```bash
cd biblioteca-api
```

---

## Executar aplicação

### Windows

```bash
mvnw spring-boot:run
```

### Linux / Mac

```bash
./mvnw spring-boot:run
```

---

# 🔥 Próximas Melhorias

- PostgreSQL
- Docker
- Swagger refinado
- Deploy online
- CI/CD
- Logs centralizados
- Observabilidade
- Monitoramento

---

# 👨‍💻 Autor

Desenvolvido por **Leandro Gonçalves**.

---

# 📖 Objetivo do Projeto

Este projeto foi desenvolvido com foco em:

- Estudos avançados em Backend Java
- Boas práticas de arquitetura
- APIs REST profissionais
- Segurança com JWT
- Clean Architecture
- Portfólio profissional

---

# ✨ Inspiração

> “O conhecimento disciplina a mente e ilumina o espírito.”

Projeto inspirado nos ensinamentos do Mestre Ramatis, valorizando evolução contínua, organização, responsabilidade e aprendizado através da tecnologia.


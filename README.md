# 📚 Biblioteca Anandamoyi API

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![Docker](https://img.shields.io/badge/Docker-enabled-2496ED)
![JWT](https://img.shields.io/badge/Auth-JWT-black)
![Tests](https://img.shields.io/badge/Tests-90-success)
![Coverage](https://img.shields.io/badge/Coverage-70%25-brightgreen)
![Build](https://img.shields.io/badge/Build-Passing-success)

---

# 📖 Sobre o Projeto

A **Biblioteca Anandamoyi API** é uma API REST para gerenciamento de biblioteca desenvolvida com **Java + Spring Boot**, utilizando princípios de **Clean Architecture**, autenticação com **JWT**, controle de empréstimos, devoluções e multas automáticas.

Projeto desenvolvido para estudos, portfólio e aprimoramento profissional, inspirado nos ensinamentos do **Mestre Ramatis** e nos princípios de organização, disciplina e evolução contínua através do conhecimento.

---

# 🚀 Tecnologias Utilizadas

* Java 17
* Spring Boot 3
* Spring Security
* JWT Authentication
* Spring Data JPA
* Hibernate
* PostgreSQL
* H2 Database
* Maven
* Docker
* JUnit 5
* Mockito
* MockMvc
* JaCoCo
* Swagger / OpenAPI

---

# 🏗️ Arquitetura

O projeto foi estruturado seguindo princípios de:

* Clean Architecture
* SOLID
* Separation of Concerns
* RESTful API Design

Separando responsabilidades em camadas independentes:

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

# 🔐 Segurança

A aplicação utiliza:

* JWT Authentication
* Spring Security
* Controle de acesso por Roles
* Endpoints protegidos
* Filtro JWT customizado
* Tratamento global de exceções

Perfis disponíveis:

* ADMIN
* BIBLIOTECARIO
* LEITOR

---

# 🧪 Testes e Qualidade

O projeto possui:

✅ Testes unitários
✅ Testes de controllers
✅ Testes de integração
✅ Testes de segurança
✅ Cobertura de código com JaCoCo

## 📊 Cobertura Atual

* Instructions: 70%
* Methods: 67%
* Classes: 86%

Gerar relatório:

```bash
mvn clean verify
```

Abrir relatório:

```text
target/site/jacoco/index.html
```

---

# 📦 Funcionalidades

## 👤 Usuários

* Cadastro de usuários
* Perfis:

    * ADMIN
    * BIBLIOTECARIO
    * LEITOR
* Validação de email
* Validação de senha
* Controle de usuários ativos

---

## 📚 Livros

* Cadastro de livros
* Cadastro de edições
* Controle de exemplares
* Disponibilidade automática
* Busca por ID
* Listagem completa

---

## 🔄 Empréstimos

* Empréstimo de exemplares
* Controle de disponibilidade
* Devolução de livros
* Controle de empréstimos ativos
* Controle de empréstimos devolvidos
* Controle de empréstimos atrasados
* Paginação
* Filtros dinâmicos

---

## 💰 Multas

* Cálculo automático de multa
* Controle de atraso
* Registro automático da data de devolução

---

# 🔑 Roles do Sistema

| Role          | Permissões                  |
| ------------- | --------------------------- |
| ADMIN         | Controle total do sistema   |
| BIBLIOTECARIO | Gerenciamento da biblioteca |
| LEITOR        | Consulta e empréstimos      |

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

### Response

```json
{
  "token": "jwt-token"
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

# 🐳 Docker

Subir ambiente completo:

```bash
docker-compose up -d
```

---

# ⚙️ Variáveis de Ambiente

Exemplo:

```env
DB_URL=jdbc:postgresql://localhost:5432/biblioteca
DB_USERNAME=admin
DB_PASSWORD=123456
JWT_SECRET=segredo-super-seguro
```

---

# ▶️ Executando o Projeto

## Clonar repositório

```bash
git clone https://github.com/CabeloSG/biblioteca-anandamoyi-api.git
```

---

## Entrar na pasta

```bash
cd biblioteca-anandamoyi-api
```

---

## Rodar aplicação

```bash
mvn spring-boot:run
```

---

# 📑 Swagger

Documentação disponível em:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# 📌 CI/CD

O projeto possui integração contínua com GitHub Actions.

Pipeline automatizada:

* Build
* Testes
* JaCoCo

Arquivo:

```text
.github/workflows/ci.yml
```

---

# 👨‍💻 Autor

Desenvolvido por **Leandro Gonçalves**

* GitHub: https://github.com/CabeloSG

---

# 📄 Licença

Projeto desenvolvido para fins educacionais e portfólio profissional.

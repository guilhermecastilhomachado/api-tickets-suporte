# API de Tickets de Suporte

[![Java](https://img.shields.io/badge/Java-17-007396?logo=java&logoColor=white)](https://adoptium.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Build](https://img.shields.io/badge/build-maven-2C3E50)](https://maven.apache.org/)
[![Tests](https://img.shields.io/badge/tests-JUnit%205-25A162?logo=junit5&logoColor=white)](https://junit.org/junit5/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-dev-336791?logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![H2](https://img.shields.io/badge/H2-test-1F5AA5)](https://www.h2database.com/)

API REST para abertura e acompanhamento de chamados de suporte, com foco em organizacao em camadas, validacoes, tratamento global de erros e testes automatizados.

---

## Sumario
- [Visao geral](#visao-geral)
- [Destaques](#destaques)
- [Tecnologias](#tecnologias)
- [Modelagem do dominio (resumo)](#modelagem-do-dominio-resumo)
- [Estrutura do projeto](#estrutura-do-projeto)
- [Documentacao (Swagger)](#documentacao-swagger)
- [Endpoints principais](#endpoints-principais)
- [Perfis de configuracao](#perfis-de-configuracao)
- [Como executar (dev)](#como-executar-dev)
- [Como executar os testes](#como-executar-os-testes)
- [Tratamento de erros](#tratamento-de-erros)

---

## Visao geral
A API permite criar, listar, atualizar e finalizar chamados de suporte, alem de adicionar comentarios. O projeto foi pensado para uso academico e portfolio, com boas praticas de configuracao por ambiente.

---

## Destaques
- CRUD completo de chamados com atualizacao de status
- Comentarios vinculados ao chamado
- Validacoes de entrada e respostas de erro padronizadas
- Separacao de ambientes: `dev`, `test`, `prod`
- Testes unitarios e de integracao com MockMvc
- Documentacao interativa via Swagger / OpenAPI

---

## Tecnologias
- Java 17
- Spring Boot (Web, Data JPA)
- PostgreSQL (dev)
- H2 Database (test)
- Maven
- JUnit 5, Mockito, MockMvc
- Docker / Docker Compose
- Swagger / OpenAPI

---

## Modelagem do dominio (resumo)
Relacoes principais:
- `Chamado (1) -> (N) Comentario`

---

## Estrutura do projeto
```text
src/main/java/br/ufu/apiticketssuporte
├── controlador
├── enumeracao
├── excecao
├── modelo
├── repositorio
└── servico
```

---

## Documentacao (Swagger)
- Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## Endpoints principais
- `POST /chamados`
- `GET /chamados`
- `GET /chamados/{id}`
- `PUT /chamados/{id}`
- `PATCH /chamados/{id}/status?status=FINALIZADO`
- `GET /chamados/status/{status}`
- `POST /chamados/{chamadoId}/comentarios`
- `GET /chamados/{chamadoId}/comentarios`

### Exemplo de requisicao (cadastro de chamado)
```json
{
  "titulo": "Erro ao acessar o sistema",
  "descricao": "O usuario nao consegue entrar no sistema com sua senha.",
  "nomeSolicitante": "Guilherme Castilho",
  "emailSolicitante": "guilherme@email.com",
  "prioridade": "ALTA"
}
```

---

## Perfis de configuracao
O projeto utiliza perfis para separar configuracoes por ambiente:
- `dev`: usa PostgreSQL (Docker)
- `test`: usa H2 em memoria (rodando testes)
- `prod`: preparado para variaveis de ambiente

Arquivos:
- `application.properties`: configuracoes comuns
- `application-dev.properties`: conexao com PostgreSQL
- `application-test.properties`: H2 em memoria
- `application-prod.properties`: variaveis de ambiente

Variaveis esperadas em `prod`:
- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

---

## Como executar (dev)
1) Suba o banco com Docker:
```bash
docker compose up -d
```

2) Rode a aplicacao pela classe principal:
- `ApiDeTicketsDeSuporteApplication`

---

## Como executar os testes
### Windows (PowerShell)
```powershell
.\mvnw.cmd test
```

### Git Bash
```bash
./mvnw test
```

---

## Tratamento de erros
A API possui tratamento global de excecoes para padronizar respostas de erro.

### Exemplo de erro de validacao
```json
{
  "dataHora": "2023-10-14T20:12:10",
  "status": 400,
  "error": "Erro de validacao",
  "message": "Um ou mais campos estao invalidos",
  "caminho": "/chamados",
  "campos": {
    "titulo": "O titulo e obrigatorio."
  }
}
```

---

Projeto desenvolvido para fins acadêmicos/portfólio.
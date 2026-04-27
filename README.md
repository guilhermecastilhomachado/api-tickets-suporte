# API de Tickets de Suporte

## Objetivo
Projeto de portfólio acadêmico desenvolvido para praticar conceitos de desenvolvimento back-end com **Java**, **Spring Boot**, **PostgreSQL** e **Docker**, simulando um sistema de abertura e acompanhamento de chamados de suporte.

O projeto foi construído com foco em organização em camadas, persistência de dados, versionamento com Git e documentação para portfólio profissional.

## Tecnologias utilizadas
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Docker
- Docker Compose
- Maven
- Lombok
- Swagger / OpenAPI
- H2 Database (testes)
- JUnit 5
- Mockito
- MockMvc

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
## Funcionalidades Implementadas
- Cadastro de chamados
- Listagem de chamados
- Busca de chamado por ID
- Atualização de chamado
- Atualização de status
- Filtro por status
- Integração com PostgreSQL
- Testes dos endpoints via Swagger
- Cadastro de comentários por chamado
- Listagem de comentários por chamado
- Tratamento global de exeções
- Resposta padronizada para erros da API
- Validações de campos com mensagens organizadas
- Testes automatizados unitários
- Teste de integração dos endpoints
- Separação de configurações por ambiente (dev, test e prod)

## Endpoints disponíveis
- POST /chamados
- GET /chamados
- GET /chamados/{id}
- PUT /chamados/{id}
- PATCH /chamados/{id}/status?status=FINALIZADO
- GET /chamados/status/{status}
- POST /chamados/{chamadoId}/comentarios
- GET /chamados/{chamadoId}/comentarios

## Exemplo de requisição para cadastro
```json
{
  "titulo": "Erro ao acessar o sistema",
  "descricao": "O usuario nao consegue entrar no sistema com sua senha.",
  "nomeSolicitante": "Guilherme Castilho",
  "emailSolicitante": "guilherme@email.com",
  "prioridade": "ALTA"
}
```

## Tratamento de erros
A API possui tratamento global de exeções para melhorar a organização das respostas em cenários de falha.

### Casos tratados
- Recurso nao encontrado (404 Not Found)
- Erro de validação (400 Bad Request)
- Erro interno do servidor (500 Internal Server Error)


### Exemplo de erro de validação
```json
{
  "dataHora": "2023-10-14T20:12:10",
  "status": 400,
  "error": "Erro de validação",
  "message": "Um ou mais campos estao invalidos",
  "caminho": "/chamados",
  "campos": {
    "titulo": "O titulo e obrigatorio."
  }
}
```

## Perfis de configuração
O projeto utiliza perfis para separar as configurações conforme o ambiente de execução:

- `dev`: ambiente local com PostgreSQL via Docker
- `test`: ambiente de testes automatizados com banco H2 em memória
- `prod`: ambiente preparado para uso com variáveis de ambiente

O perfil padrão do projeto é `dev`.

## Testes automatizados
O projeto possui testes automatizados para validar regras de negócio e comportamento dos endpoints.

### Tipos de testes implementados
- Teste unitários dos serviços
- Teste de integração com MockMvc
- Validação de erros de entrada da API

### Como executar os testes
No terminal:
```bash
mvnw.cmd test
```
Ou
```bash
.\mvnw.cmd test
```

## Como executar o projeto
1. Clone o repositório
```git clone
git clone https://github.com/guilhermecastilhomachado/api-tickets-suporte.git
cd api-tickets-suporte
```
2. Subir o banco de dados com Docker
```bash
docker compose up -d
```
3. Verificar se o container está em execução
```bash
docker compose ps
```
4. Configurar o arquivo application.properties

Utilizar a conexão local com o PostgreSQL do container Docker:
```properties
spring.application.name=api_tickets_suporte

spring.datasource.url=jdbc:postgresql://localhost:5433/api_tickets_suporte
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

springdoc.swagger-ui.path=/swagger-ui.html
```
5. Executar a aplicação

Rodar a classe principal: "ApiDeTicketsDeSuporteApplication"

## Como testar a API

Com a aplicação em execução, acessar:

http://localhost:8080/chamados

http://localhost:8080/swagger-ui.html

Caso o primeiro link retorne [], a API está funcionando corretamente.

Testes realizados
- Cadastro de chamado via Swagger
- Listagem de chamados
- Busca por ID
- Atualização de status para FINALIZADO

Aprendizados desenvolvidos
- Construção de API REST com Spring Boot
- Persistência de dados com JPA e PostgreSQL
- Organização em camadas
- Uso de Docker para infraestrutura local
- Teste de endpoints com Swagger
- Versionamento com Git e GitHub
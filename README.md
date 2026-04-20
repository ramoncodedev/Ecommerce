# Ecommerce API

REST API para um sistema de e-commerce desenvolvido com Spring Boot.

## Tecnologias

- Java 21
- Spring Boot 4.0.5
- Spring Data JPA
- Spring Web MVC
- Flyway (migrações de banco de dados)
- PostgreSQL
- Lombok

## Configuração

Crie um arquivo `application.yaml` na pasta `src/main/resources/` com as configurações do banco de dados:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/ecommerce
    username: seu_usuario
    password: sua_senha
  jpa:
    hibernate:
      ddl-auto: validate
  flyway:
    enabled: true
```

## Executar

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

## Endpoints

### Usuários
- `GET /api/users` - Listar usuários
- `GET /api/users/{id}` - Buscar usuário por ID
- `POST /api/users` - Criar usuário
- `PUT /api/users/{id}` - Atualizar usuário
- `DELETE /api/users/{id}` - Deletar usuário

### Produtos
- `GET /api/products` - Listar produtos
- `GET /api/products/{id}` - Buscar produto por ID
- `POST /api/products` - Criar produto
- `PUT /api/products/{id}` - Atualizar produto
- `DELETE /api/products/{id}` - Deletar produto

### Categorias
- `GET /api/categories` - Listar categorias
- `GET /api/categories/{id}` - Buscar categoria por ID
- `POST /api/categories` - Criar categoria
- `PUT /api/categories/{id}` - Atualizar categoria
- `DELETE /api/categories/{id}` - Deletar categoria

### Pedidos
- `GET /api/orders` - Listar pedidos
- `GET /api/orders/{id}` - Buscar pedido por ID
- `POST /api/orders` - Criar pedido
- `PUT /api/orders/{id}` - Atualizar pedido

### Pagamentos
- `GET /api/payments` - Listar pagamentos
- `GET /api/payments/{id}` - Buscar pagamento por ID
- `POST /api/payments` - Criar pagamento
- `PUT /api/payments/{id}` - Atualizar pagamento
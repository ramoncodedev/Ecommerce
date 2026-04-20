# Conexão Frontend ↔ Backend

## Pré-requisitos

- Backend rodando na porta `8080`
- Frontend rodando na porta `3000` (ou outra)

## Configuração

### 1. CORS (Já configurado)

O backend já está configurado para aceitar requisições dos origins:
- `http://localhost:3000`
- `http://localhost:8080`

Para adicionar outros origins, edite:
```
src/main/java/com/ramondev/ecommerce/config/CorsConfig.java
```

### 2. URL base no Frontend

Configure a URL base do backend no seu frontend:

```javascript
// axios ou fetch
const API_URL = 'http://localhost:8080/api';
```

## Endpoints Disponíveis

| Recurso | Endpoint |
|---------|----------|
| Users | `/api/users` |
| Products | `/api/products` |
| Categories | `/api/categories` |
| Orders | `/api/orders` |
| Payments | `/api/payments` |

## Exemplos de Requisição

### GET (listar)
```javascript
fetch('http://localhost:8080/api/products')
  .then(res => res.json())
  .then(data => console.log(data));
```

### POST (criar)
```javascript
fetch('http://localhost:8080/api/products', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ name: 'Produto', price: 99.90 })
})
  .then(res => res.json())
  .then(data => console.log(data));
```

### PUT (atualizar)
```javascript
fetch('http://localhost:8080/api/products/1', {
  method: 'PUT',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({ name: 'Produto Atualizado', price: 149.90 })
});
```

### DELETE (remover)
```javascript
fetch('http://localhost:8080/api/products/1', {
  method: 'DELETE'
});
```

## Observações

- **Autenticação**: Ainda não está implementada. Em produção, adicionar Spring Security.
- **Headers**: Inclua `Content-Type: application/json` em requisições com corpo.
- **Credenciais**: CORS permite credentials (`allowCredentials: true`).

## Testando a API

Use curl ou Postman:

```bash
curl http://localhost:8080/api/products
curl http://localhost:8080/api/categories
```
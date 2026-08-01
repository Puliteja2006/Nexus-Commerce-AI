# Postman Collection & API Test Guide 📬

This document explains how to import, configure, and execute the Postman Collection for **NexusCommerce AI**.

---

## 1. Import Steps

1. Open **Postman Desktop** or **Postman Web**.
2. Click **Import** in the top left menu.
3. Select **Raw Text** or import file and paste the Postman collection environment variables below.
4. Set the Postman Environment variable `baseUrl` to `http://localhost:8080/api/v1`.

---

## 2. Environment Variables

| Variable Name | Value | Description |
|---|---|---|
| `baseUrl` | `http://localhost:8080/api/v1` | Local or Render API base URL |
| `authToken` | `{{jwt_token}}` | Auto-set upon `POST /auth/login` |
| `sessionId` | `sess_98492048` | Guest cart session tracking |

---

## 3. Sample Execution Order

1. **Auth - Login Customer**:
   - `POST {{baseUrl}}/auth/login`
   - Test Script automatically captures token: `pm.environment.set("authToken", pm.response.json().data.token);`
2. **Products - Filter Catalog**:
   - `GET {{baseUrl}}/search?q=laptop&minRating=4.0`
3. **Cart - Add Item**:
   - `POST {{baseUrl}}/cart/items`
4. **Checkout - Place Order**:
   - `POST {{baseUrl}}/orders/checkout` (Header: `Authorization: Bearer {{authToken}}`)
5. **AI Assistant - Prompt Conversational AI**:
   - `POST {{baseUrl}}/ai/chat`

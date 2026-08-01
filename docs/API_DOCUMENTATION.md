# Complete REST API Documentation 🔌

This document provides the complete RESTful API reference for **NexusCommerce AI** across all 21 modules.

**Base API URL**: `http://localhost:8080/api/v1` (Dev) or `https://nexuscommerce-backend.onrender.com/api/v1` (Prod)

---

## 1. Authentication Module (`/auth`)

### 1.1 Register User
- **URL**: `POST /auth/register`
- **Auth**: Public
- **Request Body**:
```json
{
  "firstName": "Alex",
  "lastName": "Rivera",
  "email": "alex@nexuscommerce.com",
  "password": "Password@123"
}
```
- **Response**: `201 Created`
```json
{
  "success": true,
  "message": "User registered successfully!",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "type": "Bearer",
    "id": "a1b2c3d4-...",
    "email": "alex@nexuscommerce.com",
    "role": "ROLE_CUSTOMER"
  }
}
```

### 1.2 User Login
- **URL**: `POST /auth/login`
- **Auth**: Public
- **Request Body**:
```json
{
  "email": "alex@nexuscommerce.com",
  "password": "Password@123"
}
```
- **Response**: `200 OK`

---

## 2. Product Catalog & Search (`/products` & `/search`)

### 2.1 Get Products (Multi-Criteria Filter)
- **URL**: `GET /search?q=laptop&category=electronics&minPrice=500&maxPrice=3000&minRating=4.0&inStock=true&sortBy=price_asc&page=0&size=12`
- **Auth**: Public
- **Response**: `200 OK`
```json
{
  "success": true,
  "data": {
    "content": [
      {
        "id": "e8a9f000-...",
        "name": "Nexus Pro Book 16 AI Workstation",
        "slug": "nexus-pro-book-16-ai-workstation",
        "price": 1899.99,
        "rating": 4.9,
        "reviewCount": 18,
        "stockQuantity": 15,
        "categoryName": "Electronics"
      }
    ],
    "page": 0,
    "size": 12,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

### 2.2 Search Auto-Complete Suggestions
- **URL**: `GET /search/suggestions?q=head`
- **Auth**: Public
- **Response**: `200 OK`

---

## 3. Shopping Cart Module (`/cart`)

### 3.1 Get Cart
- **URL**: `GET /cart`
- **Auth**: Public / Optional Session ID (`X-Session-Id` header)
- **Response**: `200 OK`

### 3.2 Add Item to Cart
- **URL**: `POST /cart/items`
- **Auth**: Public / Optional Session ID
- **Request Body**:
```json
{
  "productId": "e8a9f000-...",
  "quantity": 1
}
```
- **Response**: `200 OK`

---

## 4. Order & Checkout Engine (`/orders`)

### 4.1 Place Checkout Order
- **URL**: `POST /orders/checkout`
- **Auth**: Bearer Token (`ROLE_CUSTOMER`)
- **Request Body**:
```json
{
  "addressId": "c3d4e5f6-...",
  "paymentMethod": "CREDIT_CARD",
  "shippingOptionId": "STD_GROUND",
  "couponCode": "WELCOME10"
}
```
- **Response**: `201 Created`
```json
{
  "success": true,
  "message": "Order placed successfully!",
  "data": {
    "orderNumber": "ORD-20260726-8849",
    "status": "PROCESSING",
    "paymentStatus": "COMPLETED",
    "totalAmount": 1724.99
  }
}
```

---

## 5. Payment Gateway Module (`/payments`)

### 5.1 Create Payment Intent
- **URL**: `POST /payments/create-intent`
- **Auth**: Bearer Token
- **Request Body**:
```json
{
  "amount": 1724.99,
  "paymentMethod": "CREDIT_CARD"
}
```
- **Response**: `200 OK`
```json
{
  "success": true,
  "data": {
    "clientSecret": "secret_8492048209_secret_key",
    "transactionId": "txn_credit_card_98492048",
    "status": "PENDING"
  }
}
```

### 5.2 Payment Webhook Listener
- **URL**: `POST /payments/webhook`
- **Auth**: Public Webhook Callback
- **Request Body**:
```json
{
  "transactionId": "txn_credit_card_98492048",
  "status": "COMPLETED",
  "gatewayEvent": "charge.succeeded"
}
```
- **Response**: `200 OK`

---

## 6. AI Recommendation & Assistant (`/ai`)

### 6.1 Conversational AI Assistant Chat
- **URL**: `POST /ai/chat`
- **Auth**: Public
- **Request Body**:
```json
{
  "message": "Suggest AI Workstations for deep learning under $2000"
}
```
- **Response**: `200 OK`

---

## 7. System Health Probe (`/health`)

### 7.1 Health Status Check
- **URL**: `GET /health`
- **Auth**: Public
- **Response**: `200 OK`
```json
{
  "success": true,
  "data": {
    "status": "UP",
    "application": "nexuscommerce-backend",
    "version": "1.0.0",
    "databaseStatus": "CONNECTED"
  }
}
```

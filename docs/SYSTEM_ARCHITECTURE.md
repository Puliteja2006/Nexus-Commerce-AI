# System Architecture Documentation 📐

This document describes the high-level and low-level architectural design of **NexusCommerce AI**, a multi-tenant enterprise e-commerce platform built on Spring Boot, React, and PostgreSQL.

---

## 1. High-Level Architecture (HLA)

NexusCommerce AI utilizes a clean decoupled client-server architecture with stateless JWT security, RESTful JSON interfaces, and PostgreSQL relational storage.

```
+-----------------------------------------------------------------------------------+
|                                 CLIENT LAYER                                      |
|                                                                                   |
|   +-----------------------+   +------------------------+   +------------------+   |
|   |  Desktop Web Browser  |   |   Mobile Web Browser   |   |   Postman / API  |   |
|   +-----------+-----------+   +-----------+------------+   +--------+---------+   |
+---------------+---------------------------+-------------------------+-------------+
                |                           |                         |
                +------------------- HTTP / HTTPS --------------------+
                                            |
                                  JSON REST APIs & CORS
                                            v
+-----------------------------------------------------------------------------------+
|                                 API GATEWAY / SECURITY                            |
|                                                                                   |
|   +---------------------------------------------------------------------------+   |
|   |                  Spring Security 6 Stateless JWT Filter                    |   |
|   +---------------------------------------------------------------------------+   |
+-------------------------------------------+---------------------------------------+
                                            |
                                            v
+-----------------------------------------------------------------------------------+
|                                 BUSINESS LOGIC LAYER                              |
|                                                                                   |
|   +-------------------+  +------------------+  +-------------------+              |
|   |   User & Auth     |  | Product & Variant|  | Order & Checkout  |              |
|   |   Service         |  | Service          |  | Service           |              |
|   +-------------------+  +------------------+  +-------------------+              |
|                                                                                   |
|   +-------------------+  +------------------+  +-------------------+              |
|   | Inventory & Stock |  | AI Vector Engine |  | Payment & Gateway |              |
|   | Service           |  | Service          |  | Service           |              |
|   +-------------------+  +------------------+  +-------------------+              |
|                                                                                   |
|   +-------------------+  +------------------+  +-------------------+              |
|   | BI Analytics      |  | Review & Rating  |  | Spring AOP Audit  |              |
|   | Service           |  | Service          |  | Interceptor       |              |
|   +-------------------+  +------------------+  +-------------------+              |
+-------------------------------------------+---------------------------------------+
                                            |
                                  Spring Data JPA / ORM
                                            v
+-----------------------------------------------------------------------------------+
|                                 DATA PERSISTENCE LAYER                            |
|                                                                                   |
|   +---------------------------------------------------------------------------+   |
|   |                  Railway PostgreSQL 16 Relational Database                 |   |
|   +---------------------------------------------------------------------------+   |
+-----------------------------------------------------------------------------------+
```

---

## 2. Low-Level Architecture (LLA) & Layer Breakdown

The backend follows the standard Spring Boot layered design pattern:

```
[ HTTP Request ]
       |
       v
[ Controller Layer ]  ---> Validates DTOs (@Valid), maps HTTP routes
       |
       v
[ Service Layer ]     ---> Business logic, transactions (@Transactional), AI vector math
       |
       v
[ Repository Layer ]  ---> Spring Data JPA Specifications & custom JPQL queries
       |
       v
[ Database (PostgreSQL) ]
```

---

## 3. Authentication & Security Flow

```
User Login Request (email, password)
       |
       v
[ AuthController ] -> [ AuthenticationManager ] -> [ CustomUserDetailsService ]
       |
       v
Check BCrypt Password Match
       |
       +---> Match Failed: Throw BadCredentialsException (HTTP 401)
       |
       +---> Match Success: Generate 256-bit Signed JWT Token
                                   |
                                   v
Return JwtAuthResponse (token, userRole, email)
                                   |
                                   v
Client stores Token in LocalStorage & attaches "Authorization: Bearer <token>" to subsequent HTTP requests.
```

---

## 4. Request Interception Pipeline & AOP Audit Aspect

```
Incoming HTTP Request
       |
       v
[ CorsFilter ] ---> Checks Allowed Origins (Vercel domain)
       |
       v
[ JwtAuthenticationFilter ] ---> Extracts & verifies Bearer token in SecurityContextHolder
       |
       v
[ Target Controller Method (@AuditActivity) ]
       |
       v
[ Spring AOP AuditAspect ] ---> Intercepts method completion
       |                         Extracts user email & IP address
       |                         Saves AuditLog record to DB
       v
[ HTTP Response Returned to Client ]
```

---

## 5. Database Schema Relationships Overview

- **User (1) ── (N) Order**: A customer can place multiple orders.
- **Order (1) ── (N) OrderItem**: An order contains line items.
- **Product (1) ── (N) OrderItem**: A product is referenced in order items.
- **SellerStore (1) ── (N) Product**: A merchant store owns products.
- **User (1) ── (1) Wishlist ── (N) WishlistItem**: A user owns a single wishlist containing items.
- **Product (1) ── (N) Review**: A product has customer reviews.

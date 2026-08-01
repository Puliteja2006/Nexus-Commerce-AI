# Database Documentation & ER Reference 🛢️

This document describes the relational database schema, entity-relationship structure, table definitions, foreign key constraints, and indexing strategy for **NexusCommerce AI**.

---

## 1. Entity-Relationship (ER) Diagram Description

The database schema consists of **14 relational tables**:

```
 [users] (1) <─────── (N) [addresses]
   │
   ├── (1) <─────── (N) [seller_stores] (1) <─────── (N) [products]
   │                                                      │
   ├── (1) <─────── (N) [orders] (1) <─────── (N) [order_items] ─── (N) ───┘
   │                      │                               │
   │                      └── (1) <─────── (N) [payment_transactions]
   │
   ├── (1) <─────── (1) [carts] (1) <─────── (N) [cart_items]
   │
   ├── (1) <─────── (1) [wishlists] (1) <─────── (N) [wishlist_items]
   │
   ├── (1) <─────── (N) [reviews] (N) <─────── [products]
   │
   ├── (1) <─────── (N) [notifications]
   │
   └── (1) <─────── (N) [audit_logs]
```

---

## 2. Table Specifications

### 2.1 `users`
Stores user profile information, BCrypt password hashes, and system roles.
- `id` (UUID, Primary Key)
- `email` (VARCHAR 150, Unique, Indexed)
- `password_hash` (VARCHAR 255)
- `first_name` (VARCHAR 100)
- `last_name` (VARCHAR 100)
- `role` (VARCHAR 30) – `ROLE_CUSTOMER`, `ROLE_SELLER`, `ROLE_ADMIN`
- `is_active` (BOOLEAN)
- `created_at` (TIMESTAMP)

### 2.2 `seller_stores`
Stores merchant store details and verification status.
- `id` (UUID, Primary Key)
- `user_id` (UUID, Foreign Key → `users.id`, Unique)
- `store_name` (VARCHAR 150, Unique)
- `slug` (VARCHAR 150, Unique)
- `verified` (BOOLEAN) – Set by Admin approval
- `created_at` (TIMESTAMP)

### 2.3 `categories`
Hierarchical taxonomy categories for organizing catalog items.
- `id` (UUID, Primary Key)
- `name` (VARCHAR 100)
- `slug` (VARCHAR 100, Unique)
- `parent_id` (UUID, Foreign Key → `categories.id`, Self-referencing)

### 2.4 `products`
Master catalog items with stock quantities and aggregate ratings.
- `id` (UUID, Primary Key)
- `seller_store_id` (UUID, Foreign Key → `seller_stores.id`)
- `category_id` (UUID, Foreign Key → `categories.id`)
- `name` (VARCHAR 200)
- `sku` (VARCHAR 100, Unique)
- `slug` (VARCHAR 200, Unique)
- `price` (NUMERIC 12, 2)
- `compare_at_price` (NUMERIC 12, 2)
- `stock_quantity` (INT)
- `rating` (DOUBLE PRECISION)
- `review_count` (INT)
- `featured` (BOOLEAN)
- `active` (BOOLEAN)

### 2.5 `orders` & `order_items`
Transactional checkout orders with snapshot addresses and item pricing.
- `id` (UUID, Primary Key)
- `order_number` (VARCHAR 50, Unique) – Format: `ORD-YYYYMMDD-XXXX`
- `user_id` (UUID, Foreign Key → `users.id`)
- `status` (VARCHAR 30) – `PENDING`, `PROCESSING`, `SHIPPED`, `DELIVERED`, `CANCELLED`
- `payment_status` (VARCHAR 30) – `PENDING`, `COMPLETED`, `FAILED`, `REFUNDED`
- `payment_method` (VARCHAR 30) – `CREDIT_CARD`, `PAYPAL`, `CASH_ON_DELIVERY`
- `total_amount` (NUMERIC 12, 2)

### 2.6 `payment_transactions`
Payment gateway settlement ledger linking orders to Stripe/PayPal reference tokens.
- `id` (UUID, Primary Key)
- `order_id` (UUID, Foreign Key → `orders.id`)
- `transaction_id` (VARCHAR 100, Unique)
- `payment_method` (VARCHAR 30)
- `status` (VARCHAR 30)
- `amount` (NUMERIC 12, 2)

### 2.7 `reviews`
1-5 star ratings with verified buyer validation.
- `id` (UUID, Primary Key)
- `user_id` (UUID, Foreign Key → `users.id`)
- `product_id` (UUID, Foreign Key → `products.id`)
- `rating` (INT) – 1 to 5
- `title` (VARCHAR 150)
- `comment` (VARCHAR 2000)
- `verified_purchase` (BOOLEAN)
- `helpful_votes` (INT)
- **Constraint**: Unique (`user_id`, `product_id`)

### 2.8 `notifications`
In-app user alerts.
- `id` (UUID, Primary Key)
- `user_id` (UUID, Foreign Key → `users.id`)
- `title` (VARCHAR 150)
- `message` (VARCHAR 1000)
- `type` (VARCHAR 50)
- `is_read` (BOOLEAN)

### 2.9 `audit_logs`
Immutable security activity log trail.
- `id` (UUID, Primary Key)
- `user_email` (VARCHAR 150)
- `action` (VARCHAR 100)
- `resource` (VARCHAR 100)
- `ip_address` (VARCHAR 50)
- `created_at` (TIMESTAMP)

---

## 3. Database Indexes

To optimize high-concurrency read queries, the following indexes are declared:
1. `idx_users_email`: B-Tree index on `users(email)`.
2. `idx_products_slug`: B-Tree index on `products(slug)`.
3. `idx_products_sku`: B-Tree index on `products(sku)`.
4. `idx_orders_order_number`: B-Tree index on `orders(order_number)`.
5. `idx_transactions_txn_id`: B-Tree index on `payment_transactions(transaction_id)`.

# NexusCommerce AI 🛍️🤖
> **Enterprise Multi-Vendor AI-Powered E-Commerce Marketplace Platform**

[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)](https://github.com/nexuscommerce/nexuscommerce-ai)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-green.svg)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-18.3.1-blue.svg)](https://react.dev/)
[![License](https://img.shields.io/badge/License-MIT-purple.svg)](LICENSE)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16.0-blue.svg)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-blue.svg)](https://www.docker.com/)

**NexusCommerce AI** is a state-of-the-art, full-stack enterprise e-commerce platform featuring multi-vendor storefronts, real-time AI recommendation vectors, conversational shopping assistants, multi-step checkouts, payment intent gateways (Stripe/PayPal), promo discount engines, automated stock reservations, and executive BI dashboards.

---

## 🌟 Key Features

### 🛍️ Customer Experience
- **Conversational AI Shopping Assistant**: Integrated vector-similarity shopping assistant (`/ai-assistant`) parsing natural language product prompts.
- **AI Recommendation Engine**: User category affinity scoring and similar product recommendations.
- **Multi-Criteria Search & Filtering**: Instant auto-complete search, price range sliders ($0-$5000), minimum star rating filters (4★/3★), and in-stock toggles.
- **Interactive Product Reviews**: Verified Buyer detection, 1-5 star ratings, rating breakdown distribution bars, and helpful voting.
- **4-Step Secure Checkout**: Address selection, carrier shipping tiers (Standard, Priority Express, Overnight Courier), payment method authorization, and order success receipts.
- **Wishlist & Cart**: Guest session cart tracking, authenticated user cart persistence, promo coupon redemptions (`WELCOME10`, `NEXUS20`), and wishlist bookmarks.
- **Real-Time Notification Center**: Unread badge counter, order status alerts, stock warnings, and slide-over notification drawer.

### 🏬 Multi-Merchant Storefronts & Inventory
- **Seller Registration & Verification**: Merchant store onboarding workflow with admin verification controls.
- **Warehouse Inventory Engine**: Multi-location stock level tracking, atomic order stock reservations, stock deduction, and out-of-stock safety threshold alerts.

### 🛡️ Enterprise Security & Admin Governance
- **RBAC Security & JWT**: Role-Based Access Control (`ROLE_CUSTOMER`, `ROLE_SELLER`, `ROLE_ADMIN`) with stateless JWT token authorization.
- **Spring AOP Audit Interceptor**: `@AuditActivity` aspect automatically capturing caller email, IP address, target resource, and method action into an immutable security log.
- **Executive BI Dashboards**: Gross Merchandise Value (GMV) metrics, Average Order Value (AOV) tracking, category sales distribution percentages, and Top Selling Product Leaderboard tables.

---

## 🛠️ Technology Stack

### Backend
- **Core Framework**: Java 17, Spring Boot 3.3.0, Spring Data JPA, Spring Security 6.
- **Database & Persistence**: PostgreSQL 16 / H2 Database, HikariCP Connection Pool.
- **Security & AOP**: JWT (JSON Web Tokens), BCrypt Password Encoder, Spring AOP.
- **Build & Containerization**: Maven, Multi-Stage Dockerfile, Render Blueprint (`render.yaml`).

### Frontend
- **Core Framework**: React 18, Vite, React Router DOM v6.
- **Styling & UI**: TailwindCSS, Glassmorphism UI tokens, Lucide React Icons.
- **State & HTTP**: React Context API (`AuthContext`, `CartContext`), Axios HTTP client.
- **Deployment**: Vercel Single Page Application (`vercel.json`).

---

## 🏗️ System Architecture

```
                                  +------------------------------+
                                  |    Vercel Frontend (React)   |
                                  +--------------+---------------+
                                                 |
                                      HTTPS REST | JSON Web Tokens
                                                 v
                                  +--------------+---------------+
                                  | Render Backend (Spring Boot) |
                                  |  - Security & JWT Filter     |
                                  |  - AI Vector Engine          |
                                  |  - AOP Audit Aspect          |
                                  +--------------+---------------+
                                                 |
                                   JDBC Postgres | Connection Pool
                                                 v
                                  +--------------+---------------+
                                  |   Railway PostgreSQL DB      |
                                  +------------------------------+
```

---

## 🚀 Quickstart & Local Setup

### Prerequisites
- **JDK 17** or higher
- **Node.js 18+** & `npm`
- **Maven 3.8+**
- **Docker & Docker Compose** (Optional for containerized setup)

### 1. Clone Repository
```bash
git clone https://github.com/nexuscommerce/nexuscommerce-ai.git
cd nexuscommerce-ai
```

### 2. Launch Local PostgreSQL & Backend
```bash
# Option A: Run via Docker Compose
docker-compose up -d

# Option B: Run Spring Boot directly
cd backend
mvn clean spring-boot:run
```
*Backend API available at: `http://localhost:8080/api/v1`*

### 3. Launch Frontend Development Server
```bash
cd frontend
npm install
npm run dev
```
*Frontend App available at: `http://localhost:5173`*

---

## 📚 Complete Project Documentation

All software engineering documentation files are located in the [`docs/`](./docs) folder:

- 📐 [System Architecture Documentation](docs/SYSTEM_ARCHITECTURE.md)
- 🛢️ [Database ER & Schema Reference](docs/DATABASE.md)
- 🔌 [Complete REST API Specification](docs/API_DOCUMENTATION.md)
- 📬 [Postman Collection Guide](docs/POSTMAN_COLLECTION.md)
- 📄 [Academic & Industry Project Report](docs/PROJECT_REPORT.md)
- 💼 [Portfolio, Resume & Interview Guide](docs/PORTFOLIO_GUIDE.md)
- 🧪 [Testing & QA Verification Guide](docs/TESTING.md)
- 🌐 [Production Cloud Deployment Guide](DEPLOYMENT.md)
- ✅ [Final Production Audit Checklist](docs/FINAL_CHECKLIST.md)

---

## 🌐 Production Deployment Summary

- **Frontend**: Deployed on **Vercel** (`https://nexuscommerce-frontend.vercel.app`) using `vercel.json` SPA rewrite rules.
- **Backend API**: Deployed on **Render** using a multi-stage `Dockerfile` and JDK 17 slim container runtime.
- **Database**: Provisioned on **Railway** running PostgreSQL 16.

---

## 🔮 Future Roadmap

- [ ] Spring AI OpenAI GPT-4 Embedding Vectors Integration for RAG catalog search.
- [ ] GraphQL API Endpoint Support alongside REST APIs.
- [ ] Native Mobile App (React Native / Flutter).
- [ ] Automated Redis Distributed Cache Layer for Product Aggregations.

---

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

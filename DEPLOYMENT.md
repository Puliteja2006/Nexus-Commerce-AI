# Production Cloud Deployment Guide 🚀

This document outlines the step-by-step production deployment workflow for **NexusCommerce AI**, configured for:

- **Database**: Railway PostgreSQL
- **Backend API**: Render (Multi-Stage Docker Container)
- **Frontend SPA**: Vercel

---

## 🛠️ Global Architecture Overview

```
                      +-----------------------------+
                      |     Vercel Frontend SPA     |
                      | (nexuscommerce.vercel.app)  |
                      +--------------+--------------+
                                     |
                          HTTPS REST | API Calls
                                     v
                      +--------------+--------------+
                      |    Render Backend Docker    |
                      | (onrender.com:8080/api/v1)  |
                      +--------------+--------------+
                                     |
                       JDBC Postgres | Connection Pool
                                     v
                      +--------------+--------------+
                      |    Railway PostgreSQL DB    |
                      |  (pg.railway.app:5432)      |
                      +-----------------------------+
```

---

## Step 1: Deploy Database on Railway PostgreSQL 🛢️

1. **Create Railway Account**:
   - Navigate to [https://railway.app](https://railway.app) and sign in using GitHub.

2. **Provision PostgreSQL Service**:
   - Click **+ New Project** → Select **Provision PostgreSQL**.
   - Wait for the PostgreSQL instance to initialize.

3. **Retrieve Credentials**:
   - Go to the **Variables** tab of your PostgreSQL service in Railway.
   - Note the following values:
     - `DATABASE_URL` (e.g. `postgresql://postgres:password@roundhouse.proxy.rlwy.net:12345/railway`)
     - `PGHOST` (Host proxy domain)
     - `PGPORT` (Port, e.g. `12345`)
     - `PGUSER` (User, e.g. `postgres`)
     - `PGPASSWORD` (Password)
     - `PGDATABASE` (Database name, e.g. `railway`)

---

## Step 2: Deploy Backend Container on Render 🐳

1. **Create Render Account & Connect Repository**:
   - Navigate to [https://render.com](https://render.com) and log in.
   - Click **New +** → Select **Web Service**.
   - Connect your GitHub repository containing `NexusCommerce AI`.

2. **Configure Service Build & Dockerfile**:
   - **Name**: `nexuscommerce-backend`
   - **Region**: Oregon (US West) or closest region
   - **Environment**: `Docker`
   - **Docker Command Context**: `./backend`
   - **Dockerfile Path**: `./backend/Dockerfile`
   - **Instance Type**: `Free` or `Starter`

3. **Configure Environment Variables**:
   In Render's **Environment** tab, set the following required variables:

   | Key | Example Value | Notes |
   |---|---|---|
   | `SPRING_PROFILES_ACTIVE` | `prod` | Activates `application-prod.properties` |
   | `SPRING_DATASOURCE_URL` | `jdbc:postgresql://roundhouse.proxy.rlwy.net:12345/railway` | Railway Database URL |
   | `SPRING_DATASOURCE_USERNAME` | `postgres` | Railway DB User |
   | `SPRING_DATASOURCE_PASSWORD` | `YOUR_RAILWAY_PASSWORD` | Railway DB Password |
   | `JWT_SECRET` | `ProductionJwtSecretKeyForNexusCommerceEnterpriseAi2026Secure256Bit` | 256-bit secret string |
   | `APP_CORS_ALLOWED_ORIGINS` | `https://nexuscommerce-frontend.vercel.app` | Vercel production domain |
   | `PORT` | `8080` | Container web port |

4. **Deploy Service**:
   - Click **Create Web Service**.
   - Render will execute the multi-stage `Dockerfile`, run `mvn clean package`, and launch the JDK 17 slim container.
   - Verify health probe status at: `https://nexuscommerce-backend.onrender.com/api/v1/health`

---

## Step 3: Deploy Frontend SPA on Vercel ⚡

1. **Create Vercel Account & Import Project**:
   - Navigate to [https://vercel.com](https://vercel.com) and log in.
   - Click **Add New...** → **Project** → Import your GitHub repository.

2. **Configure Project Settings**:
   - **Framework Preset**: `Vite`
   - **Root Directory**: `frontend`
   - **Build Command**: `npm run build`
   - **Output Directory**: `dist`

3. **Configure Environment Variables**:
   In Vercel's **Environment Variables** panel, add:

   | Key | Value |
   |---|---|
   | `VITE_API_BASE_URL` | `https://nexuscommerce-backend.onrender.com/api/v1` |

4. **Deploy & Verify SPA Routing**:
   - Click **Deploy**.
   - Vercel will build the frontend static assets and enforce `vercel.json` SPA rewrite rules.
   - Test navigating to `/products`, `/checkout`, and `/ai-assistant` to verify page refreshes and API communication.

---

## 🧪 Production Verification Checklist

- [x] **Backend Health Check Probe**: `GET https://nexuscommerce-backend.onrender.com/api/v1/health` returns HTTP 200 `UP`.
- [x] **Database Auto-Migration**: Spring JPA verified and updated schema tables on Railway PostgreSQL.
- [x] **CORS Origin Handshake**: Vercel domain authorized for credentials and session headers.
- [x] **Vite Single Page Application Rewrites**: Rewrites `/index.html` on direct deep link reloads.
- [x] **Zero Secrets in Source Control**: All database passwords and JWT secrets injected via platform environment variables.

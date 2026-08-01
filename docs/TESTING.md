# Testing & QA Verification Guide 🧪

This document describes the testing strategies, unit test commands, API testing guidelines, and UI validation checklists for **NexusCommerce AI**.

---

## 1. Backend Automated Unit & Integration Testing

### Run Backend Compilation Test
```bash
cd backend
mvn clean compile
```

### Run Unit Tests
```bash
mvn test
```

### Key Service Test Coverage
1. `AuthServiceImplTest`: Validates registration, duplicate email check, BCrypt password matching, and JWT token issuance.
2. `OrderServiceImplTest`: Validates cart item retrieval, stock reservation, coupon deduction math, and order creation.
3. `CouponServiceImplTest`: Validates expiration dates, minimum order subtotal thresholds, and percentage vs. fixed dollar math.

---

## 2. API Endpoint Testing Suite

| Endpoint | Method | Expected Status | Validation Criteria |
|---|---|---|---|
| `/api/v1/health` | GET | `200 OK` | Returns `status: "UP"` and `databaseStatus: "CONNECTED"` |
| `/api/v1/auth/login` | POST | `200 OK` | Returns JWT Bearer token |
| `/api/v1/search` | GET | `200 OK` | Returns paginated product content |
| `/api/v1/orders/checkout` | POST | `201 Created` | Creates order & deducts inventory stock |
| `/api/v1/payments/webhook` | POST | `200 OK` | Marks order paymentStatus as `COMPLETED` |

---

## 3. Frontend UI Testing Checklist

- [x] **Responsive Navigation**: Test mobile hamburger menu and desktop navigation bar across Viewports.
- [x] **Checkout Wizard**: Test 4-step wizard transitions (Address → Shipping → Payment → Authorization).
- [x] **Auto-Complete Search Dropdown**: Verify live suggestions popup while typing into header search bar.
- [x] **AI Assistant Chat**: Test prompts ("Suggest AI Workstations") and verify embedded product cards render.
- [x] **Notification Center**: Verify unread badge counter updates when receiving alerts.

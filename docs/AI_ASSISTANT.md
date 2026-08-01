# AI Shopping Assistant & Intelligent Customer Support Architecture 🤖💬

This document describes the architecture, context retrieval engine, prompt engineering templates, OpenAI environment integration, and API specs for the **Floating AI Shopping Assistant & Intelligent Customer Support** module of **NexusCommerce AI**.

---

## 1. High-Level AI Architecture

```
                                  +---------------------------------------+
                                  |    Floating AI Widget (React UI)      |
                                  |  (Floating trigger, expand, auto-     |
                                  |   scroll, embedded product cards)     |
                                  +-------------------+-------------------+
                                                      |
                                           HTTP POST  | /api/v1/ai/assistant/chat
                                                      v
                                  +-------------------+-------------------+
                                  |       AiAssistantController           |
                                  +-------------------+-------------------+
                                                      |
                                                      v
                                  +-------------------+-------------------+
                                  |        AiAssistantService             |
                                  +---------+-------------------+---------+
                                            |                   |
                     +----------------------+                   +---------------------+
                     |                                                                |
                     v                                                                v
   +---------------------------------+                              +-----------------------------------+
   |   Database Context Engine       |                              |     LLM Intelligence Engine       |
   | (Fetches active products, stock,|                              |  (OpenAI API / Spring AI fallback |
   |  order status, shipping policies|                              |   configured via OPENAI_API_KEY)  |
   |  & active promo coupons)        |                              +-----------------------------------+
   +---------------------------------+
```

---

## 2. Environment Variable Configurations

Set the following environment variables on Render, Railway, or local `.env`:

| Variable | Default Value | Description |
|---|---|---|
| `OPENAI_API_KEY` | `""` (Empty string triggers built-in intelligence fallback) | OpenAI API Secret Key |
| `AI_MODEL` | `gpt-3.5-turbo` | OpenAI model identifier (`gpt-4-turbo`, `gpt-3.5-turbo`) |

---

## 3. Context Intent Processing Pipeline

1. **Order Tracking Intent**: Triggered when query contains `"order"`, `"track"`, `"status"`, or `"ORD-..."`. Retrieves real-time status from `orderRepository.findByOrderNumber(...)`.
2. **Shipping & Delivery Policy Intent**: Returns Standard Ground, Priority Express, and Overnight Courier rates & timelines.
3. **Return & Refund Policy Intent**: Returns 30-day return policy and refund processing details.
4. **Payment Method Intent**: Explains Visa, MasterCard, Amex, PayPal, and COD support.
5. **Product Recommendation Intent**: Performs multi-criteria catalog matching based on category keywords (gaming laptops, photography phones, studio audio) and budget limits (`maxBudget`). Returns structured `ProductDto` lists embedded as interactive product cards in the chat UI.

---

## 4. API Endpoints

- `POST /api/v1/ai/assistant/chat`: Main chat endpoint accepting customer prompt string, optional order number, category ID, and budget limits.
- `GET /api/v1/ai/assistant/policies`: Returns store policy dictionary.
- `GET /api/v1/ai/assistant/faqs`: Returns list of customer FAQs.

# AI Vision, Voice & Smart Multimodal Search Architecture 🎙️📷

This document describes the architecture, Web Speech API integration, vision OCR pipelines, side-by-side product comparison engine, and search analytics for **NexusCommerce AI**.

---

## 1. Multimodal Search Interfaces

NexusCommerce AI provides three search discovery modes:

1. **Voice Search & Speech-to-Text Shopping Assistant**:
   - Integrated in [Header.jsx](file:///c:/Users/pulis/Desktop/E-COMMERCE%20AI/frontend/src/components/common/Header.jsx) using Web Speech API (`SpeechRecognition` / `webkitSpeechRecognition`).
   - Clicking the microphone icon activates speech recognition, converts voice queries to text, and navigates directly to matching search results.

2. **AI Vision & Image OCR Search**:
   - Modal component [ImageSearchModal.jsx](file:///c:/Users/pulis/Desktop/E-COMMERCE%20AI/frontend/src/components/common/ImageSearchModal.jsx) accepting image uploads or camera captures.
   - Extracts OCR text labels and matches color/category feature vectors against live catalog items via `POST /api/v1/ai/vision/image-search`.

3. **AI Side-by-Side Product Comparison Matrix**:
   - Modal component [ProductComparisonModal.jsx](file:///c:/Users/pulis/Desktop/E-COMMERCE%20AI/frontend/src/components/common/ProductComparisonModal.jsx) allowing customers to select two products and generate AI winner evaluations, pros/cons lists, and spec matrices (`POST /api/v1/ai/vision/compare`).

---

## 2. API Endpoints

- `POST /api/v1/ai/vision/image-search`: Accepts image Base64/URL and OCR label text; returns matching catalog products.
- `POST /api/v1/ai/vision/compare`: Accepts `productId1` and `productId2`; returns AI summary evaluation, pros/cons, and spec comparison table.
- `GET /api/v1/ai/vision/trending`: Returns top trending search terms.
- `GET /api/v1/ai/vision/analytics`: Returns search query analytics metrics.

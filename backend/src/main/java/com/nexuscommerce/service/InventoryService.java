package com.nexuscommerce.service;

import com.nexuscommerce.dto.inventory.InventoryLogDto;
import com.nexuscommerce.dto.inventory.InventoryStatusDto;
import com.nexuscommerce.dto.inventory.UpdateStockRequest;

import java.util.List;
import java.util.UUID;

public interface InventoryService {

    List<InventoryStatusDto> getSellerInventoryStatus(String sellerEmail);

    List<InventoryStatusDto> getLowStockAlerts(String sellerEmail, int threshold);

    InventoryStatusDto updateProductStock(String sellerEmail, UUID productId, UpdateStockRequest request);

    List<InventoryLogDto> getInventoryLogsByProduct(String sellerEmail, UUID productId);

    void reserveStockForOrder(UUID productId, int quantity, String orderId);

    void releaseStockForCancelledOrder(UUID productId, int quantity, String orderId);
}

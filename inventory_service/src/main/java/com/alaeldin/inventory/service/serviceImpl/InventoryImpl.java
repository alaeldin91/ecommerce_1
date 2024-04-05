package com.alaeldin.inventory.service.serviceImpl;

import com.alaeldin.inventory.dto.InventoryResponse;
import com.alaeldin.inventory.repository.InventoryRepository;
import com.alaeldin.inventory.service.InventoryService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class InventoryImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;


    @Transactional()
    @Override
    public List<InventoryResponse> isStock(List<String> skuCode) {


        return inventoryRepository
            .findBySkuCodeIn(
            skuCode).stream().map(inventory ->
                    InventoryResponse.builder()
                      .skuCode(
                    inventory.getSkuCode()).isInStock(inventory
                                    .getQuantity() > 0).build())
                                         .toList();

    }
}

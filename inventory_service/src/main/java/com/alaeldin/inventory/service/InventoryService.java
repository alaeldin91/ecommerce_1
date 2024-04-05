package com.alaeldin.inventory.service;

import com.alaeldin.inventory.dto.InventoryResponse;

import java.util.List;


public interface InventoryService {

    List<InventoryResponse> isStock(List<String> skuCode);

}

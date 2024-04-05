package com.alaeldin.inventory.mapper;

import com.alaeldin.inventory.model.Inventory;
import com.alaeldin.inventory.dto.InventoryResponse;

public class MapperInventory {

    public static InventoryResponse mapToInventoryDto(Inventory inventory){

        InventoryResponse inventoryResponse = new InventoryResponse();
        inventoryResponse.setSkuCode(inventory.getSkuCode());

        return inventoryResponse;
    }



}

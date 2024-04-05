package com.alaeldin.inventory.util;

import com.alaeldin.inventory.model.Inventory;
import com.alaeldin.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final InventoryRepository inventoryRepository;
    @Override
    public void run(String... args) throws Exception {

        Inventory inventory = new Inventory();
        inventory.setSkuCode("Iphone_one");
        inventory.setQuantity(2);

        Inventory inventory1 = new Inventory();
        inventory1.setSkuCode("Android_one");
        inventory1.setQuantity(0);
        //Save Data In Inventory
        inventoryRepository.save(inventory);
        inventoryRepository.save(inventory1);


    }
}

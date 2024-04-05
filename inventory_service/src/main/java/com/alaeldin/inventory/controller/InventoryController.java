package com.alaeldin.inventory.controller;


import com.alaeldin.inventory.service.InventoryService;
import com.alaeldin.inventory.dto.InventoryResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/inventory")
@AllArgsConstructor
public class InventoryController {

    private final InventoryService service;
    @GetMapping()
    public ResponseEntity<List<InventoryResponse> >isInStock(@RequestParam
                                                 List<String> skuCode){
        return new ResponseEntity<>(service.isStock(skuCode),HttpStatus.OK);
    }


}

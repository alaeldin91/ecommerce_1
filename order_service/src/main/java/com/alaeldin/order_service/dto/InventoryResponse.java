package com.alaeldin.order_service.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class InventoryResponse {

    private String skuCode;
    private boolean isInStock;

}

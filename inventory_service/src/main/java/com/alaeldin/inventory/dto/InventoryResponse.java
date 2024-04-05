package com.alaeldin.inventory.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class InventoryResponse {

    private String skuCode;
    private boolean isInStock;

}

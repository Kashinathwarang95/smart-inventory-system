package com.smartinventory.inventoryservice.dto;


import lombok.Data;

@Data
public class OrderRequestDTO {
    private Long productId;
    private int quantity;
}

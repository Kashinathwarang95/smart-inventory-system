package com.smartinventory.inventoryservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;   // ✅ REQUIRED

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PLACED; // default
    }
}

package com.smartinventory.inventoryservice.repository;

import com.smartinventory.inventoryservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

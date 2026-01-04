package com.smartinventory.inventoryservice.repository;

import com.smartinventory.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}

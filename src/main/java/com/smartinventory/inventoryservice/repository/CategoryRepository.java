package com.smartinventory.inventoryservice.repository;

import com.smartinventory.inventoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}

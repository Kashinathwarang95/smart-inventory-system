package com.smartinventory.inventoryservice.mapper;

import com.smartinventory.inventoryservice.dto.CategoryDTO;
import com.smartinventory.inventoryservice.entity.Category;

public class CategoryMapper {

    // Entity → DTO
    public static CategoryDTO toCategoryDTO(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        return dto;
    }

    // DTO → Entity
    public static Category toCategoryEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        return category;
    }
}
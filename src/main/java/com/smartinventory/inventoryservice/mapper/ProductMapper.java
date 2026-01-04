package com.smartinventory.inventoryservice.mapper;

import com.smartinventory.inventoryservice.dto.CategoryDTO;
import com.smartinventory.inventoryservice.dto.ProductDTO;
import com.smartinventory.inventoryservice.entity.Category;
import com.smartinventory.inventoryservice.entity.Product;

public class ProductMapper {

    // Entity → DTO
    public static ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());

        if(product.getCategory() != null){
            CategoryDTO catDTO = CategoryMapper.toCategoryDTO(product.getCategory());
            dto.setCategory(catDTO);
        }
        return dto;
    }

    // DTO → Entity
    public static Product toProductEntity(ProductDTO dto, Category category) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(category); // link Category entity
        return product;
    }
}

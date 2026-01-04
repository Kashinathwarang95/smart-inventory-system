package com.smartinventory.inventoryservice.service;

import com.smartinventory.inventoryservice.dto.ProductDTO;
import com.smartinventory.inventoryservice.entity.Category;
import com.smartinventory.inventoryservice.entity.Product;
import com.smartinventory.inventoryservice.mapper.ProductMapper;
import com.smartinventory.inventoryservice.repository.CategoryRepository;
import com.smartinventory.inventoryservice.repository.ProductRepository;
import com.smartinventory.inventoryservice.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Save product
    public ProductDTO createProduct(ProductDTO dto) {
        Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategory().getId()));

        Product product = ProductMapper.toProductEntity(dto, category);
        Product saved = productRepository.save(product);
        return ProductMapper.toProductDTO(saved);
    }

    // Get all products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    // Get product by id
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return ProductMapper.toProductDTO(product);
    }

    // Update product
    public ProductDTO updateProduct(Long id, ProductDTO dto){
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        Category category = categoryRepository.findById(dto.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategory().getId()));

        existing.setName(dto.getName());
        existing.setPrice(dto.getPrice());
        existing.setStock(dto.getStock());
        existing.setCategory(category);

        Product updated = productRepository.save(existing);
        return ProductMapper.toProductDTO(updated);
    }

    // Delete product
    public void deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}

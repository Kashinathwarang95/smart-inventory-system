package com.smartinventory.inventoryservice.controller;

import com.smartinventory.inventoryservice.dto.ProductDTO;
import com.smartinventory.inventoryservice.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO dto){
        return productService.createProduct(dto);
    }

    @GetMapping
    public List<ProductDTO> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductDTO dto){
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return "Product deleted successfully!";
    }
}
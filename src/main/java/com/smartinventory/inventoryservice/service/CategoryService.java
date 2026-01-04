package com.smartinventory.inventoryservice.service;

import com.smartinventory.inventoryservice.entity.Category;
import com.smartinventory.inventoryservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
     private final CategoryRepository categoryRepository;

     public CategoryService (CategoryRepository categoryRepository){
         this.categoryRepository = categoryRepository;
    }

    // Save category
    public Category  saveCategory(Category category){
         return categoryRepository.save(category);
    }

    // get all category
    public List<Category>getAllCategory(){
         return categoryRepository.findAll();

    }
    //get category by id
    public Category getCategoryById(Long id){
         return categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Category not found" + id));
    }

    // delete by id
    public String deleteCategoryById(Long id){
         categoryRepository.deleteById(id);
         return "Category deleted with id: " + id;
    }

    //update by id
    public Category updateCategory(Long id, Category category) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setName(category.getName());
        existing.setDescription(category.getDescription());

        return categoryRepository.save(existing);
    }
}

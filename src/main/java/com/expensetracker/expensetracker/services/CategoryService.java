package com.expensetracker.expensetracker.services;

import com.expensetracker.expensetracker.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Method to retrieve categories with optional name filtering and pagination
    public Page<Category> getCategories(String name, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            return categoryRepository.findByNameContainingIgnoreCase(name, pageable);
        } else {
            return categoryRepository.findAll(pageable);
        }
    }

    // Method to save a new or updated category
    public void save(Category category) {
        categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // ✅ Method to check if a category with given name already exists
    public boolean existsByName(String name) {
        return categoryRepository.findByName(name).isPresent();
    }
}
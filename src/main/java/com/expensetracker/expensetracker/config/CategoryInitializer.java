package com.expensetracker.expensetracker.config;

import com.expensetracker.expensetracker.models.Category;
import com.expensetracker.expensetracker.services.CategoryService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CategoryInitializer {

    private final CategoryService categoryService;

    @Autowired
    public CategoryInitializer(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostConstruct
    public void initDefaultCategories() {
        List<String> defaultCategories = Arrays.asList(
            "Food", "Transport", "Rent", "Utilities", "Entertainment"
        );

        for (String name : defaultCategories) {
            if (!categoryService.existsByName(name)) {
                Category category = new Category();
                category.setName(name);
                categoryService.save(category);
            }
        }
    }
}
package com.example.shopping.service;

import com.example.shopping.model.entity.Category;
import com.example.shopping.model.enums.CategoryName;
import com.example.shopping.repository.CategoryRepository;
import com.example.shopping.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void dbInit() {
        if (categoryRepository.count() == 0) {
            List<Category> categoriesData = Arrays.asList(
                    new Category(CategoryName.Food),
                    new Category(CategoryName.Drink),
                    new Category(CategoryName.Household),
                    new Category(CategoryName.Other));
            this.categoryRepository.saveAllAndFlush(categoriesData);
        }
    }

    @Override
    public Category findCategoryByName(CategoryName name) {
        return this.categoryRepository.findByName(name);
    }
}

package com.example.shopping.init;

import com.example.shopping.service.interfaces.CategoryService;
import com.example.shopping.service.interfaces.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataBaseInitServiceImpl implements DataBaseInitService {
    private final CategoryService categoryService;

    public DataBaseInitServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    @PostConstruct
    public void init() {
        this.categoryService.dbInit();
    }
}
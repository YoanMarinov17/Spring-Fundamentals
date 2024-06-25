package com.example.shopping.service.interfaces;

import com.example.shopping.model.entity.Category;
import com.example.shopping.model.enums.CategoryName;

public interface CategoryService {
    void dbInit();

    Category findCategoryByName(CategoryName categoryName);
}

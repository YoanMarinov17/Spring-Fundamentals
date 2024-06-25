package com.example.shopping.repository;

import com.example.shopping.model.entity.Category;
import com.example.shopping.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    Category findByName(CategoryName name);
}
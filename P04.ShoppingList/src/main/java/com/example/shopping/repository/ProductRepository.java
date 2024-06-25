package com.example.shopping.repository;

import com.example.shopping.model.entity.Product;
import com.example.shopping.model.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findAllByCategoryName(CategoryName categoryName);
    boolean existsByName(String name);

}
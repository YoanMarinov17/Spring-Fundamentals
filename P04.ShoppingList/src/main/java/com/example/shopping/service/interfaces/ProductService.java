package com.example.shopping.service.interfaces;

import com.example.shopping.model.dtos.ProductAddDTO;
import com.example.shopping.model.entity.Product;
import com.example.shopping.model.enums.CategoryName;

import java.util.List;

public interface ProductService {
    void saveProduct(ProductAddDTO productAddDTO);

    List<Product> findAllProductsByCategoryName(CategoryName categoryName);

    void buyProduct(String productId);

    void buyAllProducts();

}

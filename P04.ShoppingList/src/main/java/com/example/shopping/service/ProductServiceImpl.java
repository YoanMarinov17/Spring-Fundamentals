package com.example.shopping.service;

import com.example.shopping.model.dtos.ProductAddDTO;
import com.example.shopping.model.entity.Product;
import com.example.shopping.model.enums.CategoryName;
import com.example.shopping.repository.ProductRepository;
import com.example.shopping.service.interfaces.CategoryService;
import com.example.shopping.service.interfaces.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveProduct(ProductAddDTO productAddDTO) {
        Product product = modelMapper.map(productAddDTO, Product.class);
        if (!this.productRepository.existsByName(productAddDTO.getName())){
            product.setCategory(this.categoryService.findCategoryByName(productAddDTO.getCategory()));
            this.productRepository.saveAndFlush(product);
        }
    }

    @Override
    public List<Product> findAllProductsByCategoryName(CategoryName categoryName) {
        return this.productRepository.findAllByCategoryName(categoryName);
    }

    @Override
    public void buyProduct(String productId) {
        this.productRepository.deleteById(productId);
    }

    @Override
    public void buyAllProducts() {
        this.productRepository.deleteAll();
    }
}

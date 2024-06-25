package com.example.shopping.controller;

import com.example.shopping.model.dtos.ProductAddDTO;
import com.example.shopping.model.entity.Product;
import com.example.shopping.model.enums.CategoryName;
import com.example.shopping.service.interfaces.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/home")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ModelAndView home(ModelAndView model){
        List<Product> foodProducts = this.productService.findAllProductsByCategoryName(CategoryName.Food);
        List<Product> drinkProducts = this.productService.findAllProductsByCategoryName(CategoryName.Drink);
        List<Product> householdProducts = this.productService.findAllProductsByCategoryName(CategoryName.Household);
        List<Product> otherProducts = this.productService.findAllProductsByCategoryName(CategoryName.Other);
        List<Product> allProducts = Stream.of(foodProducts, drinkProducts, householdProducts, otherProducts)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        BigDecimal totalPriceOfProducts = allProducts.stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addObject("foodProducts", foodProducts);
        model.addObject("drinkProducts", drinkProducts);
        model.addObject("householdProducts", householdProducts);
        model.addObject("otherProducts", otherProducts);
        model.addObject("totalPriceOfProducts", totalPriceOfProducts);
        model.setViewName("home");
        return model;
    }

    @ModelAttribute(name = "productAddDTO")
    public ProductAddDTO productAddForm(){
        return new ProductAddDTO();
    }

    @GetMapping("/addProduct")
    public ModelAndView addProduct(ModelAndView model){
        model.addObject("categoriesNameValues", CategoryName.values());
        model.setViewName("product-add");
        return model;
    }

    @PostMapping("/addProduct")
    public ModelAndView addProduct(ModelAndView model,
                                @Valid ProductAddDTO productAddDTO,
                                BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            model.addObject("categoriesNameValues", CategoryName.values());
            model.setViewName("product-add");
            return model;
        }
        this.productService.saveProduct(productAddDTO);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/buyProduct/{productId}")
    public ModelAndView buyProduct(@PathVariable String productId, ModelAndView model){
        this.productService.buyProduct(productId);
        model.setViewName("redirect:/home");
        return model;
    }

    @GetMapping("/buyAllProducts")
    public ModelAndView buyAllProducts(ModelAndView model){
        this.productService.buyAllProducts();
        model.setViewName("redirect:/home");
        return model;
    }
}

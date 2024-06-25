package com.example.shopping.model.dtos;

import com.example.shopping.model.enums.CategoryName;
import com.example.shopping.validation.uniqueProductName.UniqueProductName;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductAddDTO {

    @UniqueProductName
    @NotNull(message = "Name cannot be empty!")
    @Size(min = 3, max = 20, message = "Name length must be between 3 and 20 characters!")
    private String name;

    @NotNull(message = "Description cannot be empty!")
    @Size(min = 5, message = "Description length must be more than 5 characters!")
    private String description;

    @NotNull(message = "Price cannot be empty!")
    @Positive(message = "Price must be positive number!")
    private BigDecimal price;

    @NotNull(message = "Before cannot be empty!")
    @PastOrPresent(message = "The date cannot be in the future!")
    private LocalDateTime neededBefore;

    @NotNull(message = "Category cannot be empty!")
    private CategoryName category;
}

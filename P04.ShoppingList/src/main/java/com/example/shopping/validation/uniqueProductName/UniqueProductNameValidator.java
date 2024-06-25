package com.example.shopping.validation.uniqueProductName;

import com.example.shopping.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, String> {
    private final ProductRepository productRepository;
    private String message;

    public UniqueProductNameValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void initialize(UniqueProductName constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String productName, ConstraintValidatorContext context) {
        if (productName != null && !productRepository.existsByName(productName)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
}

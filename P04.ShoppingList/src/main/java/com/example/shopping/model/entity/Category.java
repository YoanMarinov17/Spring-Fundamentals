package com.example.shopping.model.entity;

import com.example.shopping.model.enums.CategoryName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private CategoryName name;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category(CategoryName categoryName) {
        this.name = categoryName;
    }

    public Category() {
    }
}

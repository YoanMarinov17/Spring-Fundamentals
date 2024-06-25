package com.bonappetit.model.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(unique = true, nullable = false, name = "name")
    @Enumerated(EnumType.STRING)
    private CategoryName name;

    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Recipe> recipes;




    public Category(CategoryName name, String description) {
        this.name = name;
        this.description = description;

    }



    public Category() {
        this.recipes = new HashSet<>();
    }

    public CategoryName getCategoryName() {
        return name;
    }

    public void setCategoryName(CategoryName categoryEnum) {
        this.name = categoryEnum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> receiptCategories) {
        this.recipes = receiptCategories;
    }
}

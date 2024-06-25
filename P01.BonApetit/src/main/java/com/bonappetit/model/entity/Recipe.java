package com.bonappetit.model.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes")
public class Recipe extends BaseEntity{


    @Column(nullable = false)
    private String name;

    private String ingredients;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;

    @ManyToOne
    private User addedBy;






    public void setCategory(Category category) {
        this.category = category;
    }



    public Recipe() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Category getCategory() {
        return category;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }
}

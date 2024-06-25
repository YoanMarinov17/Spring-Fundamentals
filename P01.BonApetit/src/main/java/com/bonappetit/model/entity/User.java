package com.bonappetit.model.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{


    @Column(unique = true,nullable = false)
    private String username;


    @Column(nullable = false)
    private String password;

    @Column(unique = true,nullable = false)
    private String email;


    @OneToMany(mappedBy = "addedBy")
    private Set<Recipe> addedRecipes;


    @ManyToMany
    @JoinTable(name = "user_receipe",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "receipe_id"))
    private List<Recipe> favouriteRecipes;

    public User() {
        this.addedRecipes = new HashSet<>();
        this.favouriteRecipes = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Recipe> getAddedRecipes() {
        return addedRecipes;
    }

    public void setAddedRecipes(Set<Recipe> addedRecipes) {
        this.addedRecipes = addedRecipes;
    }

    public List<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(List<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }


    public void addFavourite(Recipe recipe) {
        this.favouriteRecipes.add(recipe);
    }



}

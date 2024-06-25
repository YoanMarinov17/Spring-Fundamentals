package com.bonappetit.service;


import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.model.entity.dto.AddRecipeDto;
import com.bonappetit.repo.CategoryRepo;
import com.bonappetit.repo.RecipeRepo;
import com.bonappetit.repo.UserRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepo recipeRepo;
    private final UserRepo userRepo;
    private final LoggedUser loggedUser;

    private final CategoryRepo categoryRepo;

    public RecipeService(RecipeRepo recipeRepo, UserRepo userRepo, LoggedUser loggedUser, CategoryRepo categoryRepo) {
        this.recipeRepo = recipeRepo;
        this.userRepo = userRepo;
        this.loggedUser = loggedUser;
        this.categoryRepo = categoryRepo;
    }

    public boolean create(AddRecipeDto data) {
        if (!loggedUser.isUserLoggedIn()) {
            return false;
        }

        Optional<User> byId = userRepo.findById(loggedUser.id());

        if (byId.isEmpty()) {
            return false;
        }

        CategoryName name = data.getCategory();

        Optional<Category> byName = categoryRepo.findByName(name);

        if (byName.isEmpty()) {
            return false;
        }

        Recipe recipe = new Recipe();
        recipe.setName(data.getName());
        recipe.setIngredients(data.getIngredients());
        recipe.setCategory(byName.get());
        recipe.setAddedBy(byId.get());


        recipeRepo.save(recipe);

        return true;

    }

    public Map<CategoryName, List<Recipe>> findAllByCategory() {
        Map<CategoryName, List<Recipe>> result = new HashMap<>();

        // Взимаме всички категории от базата - Cocktail, Main, Dessert - 3бр.
        List<Category> allCategories = categoryRepo.findAll();

        for (Category cat : allCategories) {
            List<Recipe> recipes = recipeRepo.findAllByCategory(cat);

            result.put(cat.getCategoryName(), recipes); // На всяка една категория добавяме рецептите в този Мап (result)

        }

        return result;
    }

@Transactional
    public void addToFavourites(Long id, long recipeId) {
        Optional<User> userId = userRepo.findById(id);

        if (userId.isEmpty()){
            return;
        }

        Optional<Recipe> recipe = recipeRepo.findById(recipeId);


        if (recipe.isEmpty()){
            return;
        }

        userId.get().addFavourite(recipe.get());
        userRepo.save(userId.get());

    }


}

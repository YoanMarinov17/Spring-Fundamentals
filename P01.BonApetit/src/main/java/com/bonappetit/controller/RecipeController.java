package com.bonappetit.controller;


import antlr.ASTFactory;
import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.entity.dto.AddRecipeDto;
import com.bonappetit.model.entity.dto.UserRegisterDto;
import com.bonappetit.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RecipeController {

    private RecipeService recipeService;

    private final LoggedUser loggedUser;

    public RecipeController(RecipeService recipeService, LoggedUser loggedUser) {
        this.recipeService = recipeService;
        this.loggedUser = loggedUser;
    }


    @ModelAttribute("recipeData")
    public AddRecipeDto addRecipeData() {
        return new AddRecipeDto();
    }


    @GetMapping("/add-recipe")
    public  String recipe(){
        if (!loggedUser.isUserLoggedIn()){
            return "redirect:/";
        }
        return "recipe-add";
    }


    @PostMapping("/add-recipe")
    public String addRecipe(@Valid AddRecipeDto data, BindingResult bindingResult, RedirectAttributes redirectAttributes){


        if (!loggedUser.isUserLoggedIn()){
            return "redirect:/";
        }

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("recipeData", data);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeData", bindingResult);
            return "redirect:/add-recipe";
        }

        boolean success = recipeService.create(data);

        if (!success){
            redirectAttributes.addFlashAttribute("recipeData", data);

            return "redirect:/add-recipe";

        }

        return "redirect:/home";
    }


    @PostMapping("/add-to-favourites/{recipeId}")
    public String addToFavourites(@PathVariable long recipeId){

        if (!loggedUser.isUserLoggedIn()){
            return "redirect:/";
        }

        recipeService.addToFavourites(loggedUser.id(), recipeId );




return "redirect:/home";
    }
}

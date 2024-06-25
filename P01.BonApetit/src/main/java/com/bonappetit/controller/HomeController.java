package com.bonappetit.controller;


import com.bonappetit.config.LoggedUser;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.dto.RecipeInfoDto;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;

    private final RecipeService recipeService;

    private final UserService userService;

    public HomeController(LoggedUser loggedUser, RecipeService recipeService, UserService userService) {
        this.loggedUser = loggedUser;
        this.recipeService = recipeService;
        this.userService = userService;
    }


    @GetMapping("/")
    public String notLogged() {
        if (loggedUser.isUserLoggedIn()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    @Transactional
    public String logged(Model model) {
        if (!loggedUser.isUserLoggedIn()) {
            return "redirect:/";
        }

        Map<CategoryName, List<Recipe>> allRecipes = recipeService.findAllByCategory();


        List<RecipeInfoDto> favourites = userService.findFavourites(loggedUser.id())
                .stream()
                .map(RecipeInfoDto::new)
                .toList();




        List<RecipeInfoDto> cocktails = allRecipes.get(CategoryName.COCKTAIL)
                        .stream()
                                .map(RecipeInfoDto::new)
                                        .toList();



        List<RecipeInfoDto> mainDish = allRecipes.get(CategoryName.MAIN_DISH)
                .stream()
                .map(RecipeInfoDto::new)
                .toList();




        List<RecipeInfoDto> dessert = allRecipes.get(CategoryName.DESSERT)
                .stream()
                .map(RecipeInfoDto::new)
                .toList();


        model.addAttribute("cocktailsData", cocktails);
        model.addAttribute("mainDishData", mainDish);
        model.addAttribute("dessertData",dessert );
        model.addAttribute("favouriteData", favourites);


        return "home";
    }


}

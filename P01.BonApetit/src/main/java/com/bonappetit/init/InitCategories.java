package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.repo.CategoryRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class InitCategories implements CommandLineRunner {


    private final Map<CategoryName, String> categories = Map.of(
            CategoryName.COCKTAIL, "Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.",
            CategoryName.DESSERT, "Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.",
            CategoryName.MAIN_DISH, "Heart of the meal, substantial and satisfying; main dish delights taste buds."
    );

    private  final CategoryRepo categoryRepo;

    public InitCategories(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {

        long count = categoryRepo.count();

        if (count > 0) {
            return;
        }

        for (CategoryName categoryEnum : categories.keySet()) {
            Category category = new Category(categoryEnum, categories.get(categoryEnum));
            categoryRepo.save(category);
        }

    }
}
package it.marczuk.cookbookapp.controller;

import it.marczuk.cookbookapp.model.Recipe;
import it.marczuk.cookbookapp.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecipeApi {

    private final AllService allService;

    @Autowired
    public RecipeApi(AllService allService) {
        this.allService = allService;
    }

    @GetMapping("/cookbook/api/all")
    public List<Recipe> getAllRecipes() {
        return allService.getAllRecipes();
    }

    @GetMapping("/cookbook/api")
    public List<Recipe> getRecipesById(@RequestParam String name) {
        return allService.getRecipeByTitle(name);
    }

    @GetMapping("/cookbook/api/id/{id}")
    public Recipe getRecipesById(@PathVariable Long id) {
        return allService.getRecipeById(id);
    }
}

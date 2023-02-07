package it.marczuk.cookbookapp.controller;

import it.marczuk.cookbookapp.dto.RecipeDto;
import it.marczuk.cookbookapp.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final AllService allService;

    @Autowired
    public RecipeController(AllService allService) {
        this.allService = allService;
    }

    @GetMapping("/cookbook")
    public String getRecipes(@RequestParam(value = "name", required = false) String name, Model model) {
        if(name == null) {
            model.addAttribute("recipes", allService.getAllRecipes());
        } else {
            model.addAttribute("recipes", allService.getRecipeByTitle(name));
        }
        model.addAttribute("newRecipie", new RecipeDto());
        return "index";
    }

    @PostMapping("/add")
    public String addRecipe(@ModelAttribute RecipeDto recipeDto, Model model) {
        model.addAttribute("newRecipie", new RecipeDto());
        allService.addAllRecipe(recipeDto);
        return "redirect:/cookbook";
    }

    @PostMapping("/edit/{id}")
    public String editRecipe(@ModelAttribute RecipeDto recipeDto, @PathVariable Long id, Model model) {
        model.addAttribute("newRecipie", new RecipeDto());
        allService.editAllRecipe(recipeDto, id);
        return "redirect:/cookbook";
    }

    @GetMapping("/remove/{id}")
    public String deleteRecipie(@PathVariable Long id) {
        allService.deleteRecipe(id);
        allService.deleteComponents(allService.getRecipeById(id));
        return "redirect:/cookbook";
    }

    @GetMapping("/cookbook/api-info")
    public String getApiInfo() {
        return "api-info";
    }
}

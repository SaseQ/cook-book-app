package it.marczuk.cookbookapp.service;

import it.marczuk.cookbookapp.dto.RecipeDto;
import it.marczuk.cookbookapp.model.Component;
import it.marczuk.cookbookapp.model.Recipe;
import it.marczuk.cookbookapp.repository.ComponentRepo;
import it.marczuk.cookbookapp.repository.RecipeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AllServiceImpl implements AllService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyyy");

    private final ComponentRepo componentRepo;
    private final RecipeRepo recipeRepo;

    @Autowired
    public AllServiceImpl(ComponentRepo componentRepo, RecipeRepo recipeRepo) {
        this.componentRepo = componentRepo;
        this.recipeRepo = recipeRepo;
    }

    //    Recipe Service
    @Override
    public List<Recipe> getRecipeByTitle(String title) {
        return recipeRepo.getRecipeByName(title);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {
        recipeRepo.deleteById(id);
    }

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> byId = recipeRepo.findById(id);
        return byId.orElse(null);
    }

    //    Component Service
    @Override
    @Transactional
    public void deleteComponents(Recipe recipe) {
        componentRepo.deleteByRecipe(recipe);
    }

    //    All Service
    @Override
    public void addAllRecipe(RecipeDto recipeDto) {
        Recipe recipe = new Recipe(recipeDto.getName(), recipeDto.getPrepare(), LocalDateTime.now().format(formatter));
        recipeRepo.save(recipe);

        arrayOperation(recipeDto, recipe);
    }

    @Override
    @Transactional
    public void editAllRecipe(RecipeDto recipeDto, Long id) {
        Optional<Recipe> recipeOptional = recipeRepo.findById(id);
        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            recipe.setName(recipeDto.getName());
            recipe.setPrepare(recipeDto.getPrepare());
            recipeRepo.save(recipe);

            componentRepo.deleteByRecipe(recipe);
            arrayOperation(recipeDto, recipe);
        }

    }

    private void arrayOperation(RecipeDto recipeDto, Recipe recipe) {
        String[] split = recipeDto.getComponents().split("\\s*,\\s*");

        Arrays.stream(split).forEach(s -> {
            Component component = new Component(s);
            component.setRecipe(recipe);
            componentRepo.save(component);
        });
    }
}

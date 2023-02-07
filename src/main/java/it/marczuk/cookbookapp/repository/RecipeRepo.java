package it.marczuk.cookbookapp.repository;

import it.marczuk.cookbookapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    List<Recipe> getRecipeByName(String name);
}

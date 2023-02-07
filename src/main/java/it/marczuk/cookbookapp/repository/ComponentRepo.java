package it.marczuk.cookbookapp.repository;

import it.marczuk.cookbookapp.model.Component;
import it.marczuk.cookbookapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComponentRepo extends JpaRepository<Component, Long> {

    void deleteByRecipe(Recipe recipe);
}

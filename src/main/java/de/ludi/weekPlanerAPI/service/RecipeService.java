package de.ludi.weekPlanerAPI.service;

import de.ludi.weekPlanerAPI.dao.RecipeDAO;
import de.ludi.weekPlanerAPI.model.Product;
import de.ludi.weekPlanerAPI.model.Recipe;
import de.ludi.weekPlanerAPI.model.RecipeProduct;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

  private RecipeDAO recipeDAO;

  @Autowired
  public RecipeService(RecipeDAO recipeDAO) {
    this.recipeDAO = recipeDAO;
  }

  public Optional<Recipe> getRecipe(int recipeID) {
    return recipeDAO.findById(recipeID);
  }

  public List<Recipe> getAllRecipes() {
    return recipeDAO.findAll();
  }

  public Recipe updateRecipe(int recipeID, Recipe updatedRecipe) {
    return recipeDAO.findById(recipeID).map(recipe -> {
      recipe.setIngredients(updatedRecipe.getIngredients());
      recipe.setName(updatedRecipe.getName());
      recipe.setNote(updatedRecipe.getNote());
      return recipeDAO.save(recipe);
    }).orElseGet(() -> {
      updatedRecipe.setId(recipeID);
      return recipeDAO.save(updatedRecipe);
    });
  }

  public void deleteRecipe(int recipeID) {
    recipeDAO.deleteById(recipeID);
  }

  public Recipe createRecipe(Recipe recipe) {
    return recipeDAO.save(recipe);
  }

  public void deleteProductFromRecipe(int recipeID, Product product) {
    recipeDAO.findById(recipeID)
        .ifPresent(recipe ->
            recipe.getIngredients().remove(recipeDAO.findProductInRecipe(product, recipeID).get()));
  }

  public void addProductToRecipe(int recipeID, RecipeProduct product) {
    recipeDAO.findById(recipeID).ifPresent(recipe -> {
      recipe.addIngredient(product);
      recipeDAO.save(recipe);
    });
  }
}

package de.ludi.weekPlanerAPI.dao;

import de.ludi.weekPlanerAPI.model.MealPlanRecipe;
import de.ludi.weekPlanerAPI.model.Product;
import de.ludi.weekPlanerAPI.model.Recipe;
import de.ludi.weekPlanerAPI.model.RecipeProduct;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecipeDAO extends JpaRepository<Recipe, Integer> {

  @Query("select product from Recipe recipe JOIN recipe.ingredients product WHERE product.product = :product AND recipe.id = :recipeID ")
  Optional<RecipeProduct> findProductInRecipe(@Param("product") Product product, @Param("recipeID") int recipeID);
}

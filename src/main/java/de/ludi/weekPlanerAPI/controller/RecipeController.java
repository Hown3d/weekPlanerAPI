package de.ludi.weekPlanerAPI.controller;

import de.ludi.weekPlanerAPI.model.MealPlanRecipe;
import de.ludi.weekPlanerAPI.model.Product;
import de.ludi.weekPlanerAPI.model.Recipe;
import de.ludi.weekPlanerAPI.model.RecipeProduct;
import de.ludi.weekPlanerAPI.service.RecipeService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/recipeAPI", produces = { MediaType.APPLICATION_JSON_VALUE })
public class RecipeController {

  private RecipeService recipeService;

  @Autowired
  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  /**
   * get a certain Recipe specified by the recipeID <br>
   * URL: /recipeAPI/{recipeID} <br>
   * METHOD: Get
   * @param recipeID Id of the Recipe
   * @return ResponseEntity with statuscode 200 if Recipe exists and Recipe in body or 404 if Recipe doesnt exist
   */
  @GetMapping("/{recipeID}")
  public ResponseEntity<Recipe> getRecipe(@PathVariable int recipeID) {
    return ResponseEntity.of(recipeService.getRecipe(recipeID));
  }

  /**
   * Gets all Recipes<br>
   * URL: /recipeAPI/getAllRecipes<br>
   * METHOD: Get
   * @return ResponseEntity with statuscode 200 and All Recipe as a List in body
   */
  @GetMapping("/getRecipes")
  public ResponseEntity<List<Recipe>> getAllRecipes() {
    return ResponseEntity.ok(recipeService.getAllRecipes());
  }

  /**
   * Updates a Recipe specified by the recipeID <br>
   * URL: /recipeAPI/{recipeID} <br>
   * METHOD: Put
   * @param recipeID Id of the Recipe
   * @param updatedRecipe Recipe which has been updated in client
   * @return ResponseEntity with statuscode 200 and updated Recipe in body
   */
  @PutMapping("/{recipeID}")
  public ResponseEntity<Recipe> updateRecipe(
      @PathVariable int recipeID, @RequestBody Recipe updatedRecipe) {
    return ResponseEntity.ok(recipeService.updateRecipe(recipeID, updatedRecipe));
  }

  /**
   * Deletes a recipe specified by the recipeID <br>
   * URL: /recipeAPI/{recipeID} <br>
   * METHOD: Delete
   * @param recipeID Id of the recipe
   */
  @DeleteMapping("/{recipeID}")
  public void deleteRecipe(@PathVariable int recipeID) {
    recipeService.deleteRecipe(recipeID);
  }

  /**
   * creates a new recipe <br>
   * URL: /recipeAPI/createRecipe <br>
   * METHOD: Post
   * @param recipe Recipe which shall be created
   * @return ResponseEntity with statuscode 200 and newly created recipe in body
   */
  @PostMapping("/createRecipe")
  public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe) {
    return ResponseEntity.ok(recipeService.createRecipe(recipe));
  }

  @PostMapping("/addProduct/{recipeID}")
  public void addProductToRecipe(@PathVariable int recipeID, @RequestBody RecipeProduct product) {
    recipeService.addProductToRecipe(recipeID, product);
  }

  @DeleteMapping("/deleteProduct/{recipeID}")
  public void deleteRecipeFromMealPlan(@PathVariable int recipeID, @RequestBody Product product) {
    recipeService.deleteProductFromRecipe(recipeID, product);
  }
}

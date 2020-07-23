package de.ludi.weekPlanerAPI.controller;

import de.ludi.weekPlanerAPI.model.MealPlan;
import de.ludi.weekPlanerAPI.model.MealPlanRecipe;
import de.ludi.weekPlanerAPI.model.Recipe;
import de.ludi.weekPlanerAPI.service.MealPlanService;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mealPlanerAPI", produces = { MediaType.APPLICATION_JSON_VALUE })
public class MealPlanController {

  private MealPlanService mealPlanService;

  @Autowired
  public MealPlanController(MealPlanService mealPlanService) {
    this.mealPlanService = mealPlanService;
  }

  /**
   * Gets the Mealplan for the specified calendarweek
   * URL: /mealPlanerAPI <br>
   * METHOD: Get
   * @param calendarWeek The Calendar Week of the year (for example: 45)
   * @return ResponseEntity with statuscode 200 and mealplan in Body
   */
  @GetMapping
  public ResponseEntity<MealPlan> getMealPlanForWeek(@RequestParam int calendarWeek) {
    return ResponseEntity.ok(mealPlanService.getMealPlanByWeek(calendarWeek));
  }

  /**
   * Adds a recipe to a mealplan at a certain day <br>
   * URL: /mealPlanerAPI/addRecipe/{mealPlanID} <br>
   * METHOD: Post
   * @param mealPlanID Id of the mealplan to add the recipe to
   * @param recipe The Recipe to add to the Mealplan
   */
  @PostMapping("/addRecipe/{mealPlanID}")
  public void addRecipeToMealPlan(@PathVariable int mealPlanID, @RequestBody MealPlanRecipe recipe) {
    mealPlanService.addRecipeToMealPlan(mealPlanID, recipe);
  }

  /**
   * deletes a recipe at a certain day from the specified mealplan <br>
   * URL: /mealPlanerAPI/{mealPlanID} <br>
   * METHOD: Delete
   * @param mealPlanID Id of the mealplan to delete the recipe from
   * @param recipe Recipe which shall be deleted
   */
  @DeleteMapping("{mealPlanID}")
  public void deleteRecipeFromMealPlan(@PathVariable int mealPlanID, @RequestBody Recipe recipe) {
    mealPlanService.deleteRecipeFromMealPlan(mealPlanID, recipe);
  }

  /**
   * Creates a new Mealplan with CalendarWeek and Recipes <br>
   * URL: /mealPlanerAPI/createMealPlan <br>p
   * METHOD: Post
   * @param mealPlan Mealplan which shall be created
   * @return ResponseEntity with statuscode 200 and newly created mealplan in Body
   */
  @PostMapping("/createMealPlan")
  public ResponseEntity<MealPlan> createMealPlan(@RequestBody MealPlan mealPlan) {
   return ResponseEntity.ok(mealPlanService.createMealPlan(mealPlan));
  }
}

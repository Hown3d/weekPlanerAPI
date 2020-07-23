package de.ludi.weekPlanerAPI.service;

import de.ludi.weekPlanerAPI.dao.MealPlanDAO;
import de.ludi.weekPlanerAPI.model.CalendarWeek;
import de.ludi.weekPlanerAPI.model.MealPlan;
import de.ludi.weekPlanerAPI.model.MealPlanRecipe;
import de.ludi.weekPlanerAPI.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MealPlanService {

  private MealPlanDAO mealPlanDAO;

  @Autowired
  public MealPlanService(MealPlanDAO mealPlanDAO) {
    this.mealPlanDAO = mealPlanDAO;
  }

  public MealPlan getMealPlanByWeek(int calendarWeek) {
    return mealPlanDAO.findMealPlanByWeek(calendarWeek)
        .orElseGet(() -> {
          MealPlan newMealPlan = new MealPlan();
          CalendarWeek newCalendarWeek = new CalendarWeek();
          newCalendarWeek.setWeekNumber(calendarWeek);
          newMealPlan.setWeek(newCalendarWeek);
          return mealPlanDAO.save(newMealPlan);
        });
  }

  public void addRecipeToMealPlan(int mealPlanID, MealPlanRecipe recipeToAdd) {
    mealPlanDAO.findById(mealPlanID)
        .ifPresent(mealPlan -> {
          mealPlan.addRecipe(recipeToAdd);
          mealPlanDAO.save(mealPlan);
        });
  }

  public void deleteRecipeFromMealPlan(int mealPlanID, Recipe recipe) {
    mealPlanDAO.findById(mealPlanID)
        .ifPresent(mealPlan -> {
          mealPlan.getRecipes()
              .remove(mealPlanDAO.findRecipeInMealPlan(recipe, mealPlanID).get());
        });
  }

  public MealPlan createMealPlan(MealPlan mealPlan) {
    return mealPlanDAO.save(mealPlan);
  }
}

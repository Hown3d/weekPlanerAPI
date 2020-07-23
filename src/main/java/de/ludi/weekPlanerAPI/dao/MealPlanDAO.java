package de.ludi.weekPlanerAPI.dao;

import de.ludi.weekPlanerAPI.model.MealPlan;
import de.ludi.weekPlanerAPI.model.MealPlanRecipe;
import de.ludi.weekPlanerAPI.model.Recipe;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MealPlanDAO extends JpaRepository<MealPlan, Integer> {

  @Query("SELECT mealplan from MealPlan mealplan WHERE mealplan.week.weekNumber = :calendarWeek")
  Optional<MealPlan> findMealPlanByWeek(@Param("calendarWeek") int calendarWeek);

  @Query("select mealplanrecipe from MealPlanRecipe mealplanrecipe WHERE mealplanrecipe.day = :day AND mealplanrecipe.mealPlan.id = :mealPlanID")
  Optional<List<MealPlanRecipe>> findRecipeByDayAndMealPlanID(@Param("day") Date day, @Param("mealPlanID") int mealPlanID);

  @Query("select mealplanrecipe from MealPlanRecipe mealplanrecipe WHERE mealplanrecipe = :mealPlanRecipe AND mealplanrecipe.mealPlan.id = :mealPlanID")
  Optional<List<MealPlanRecipe>> findRecipeByRecipeAndMealPlanID(@Param("mealPlanRecipe") MealPlanRecipe mealPlanRecipe, @Param("mealPlanID") int mealPlanID);

  @Query("select recipes from MealPlan mealplan JOIN mealplan.recipes recipes WHERE recipes.recipe = :recipe AND mealplan.id = :mealPlanID")
  Optional<MealPlanRecipe> findRecipeInMealPlan(@Param("recipe") Recipe recipe, @Param("mealPlanID") int mealPlanID);
}

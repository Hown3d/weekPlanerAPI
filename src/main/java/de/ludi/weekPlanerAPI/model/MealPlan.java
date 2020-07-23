package de.ludi.weekPlanerAPI.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MealPlan {

  @Id
  @GeneratedValue
  @Column(name = "mealPlan_id")
  private int id;

  @Embedded
  private CalendarWeek week;

  @OneToMany(mappedBy = "mealPlan", cascade = CascadeType.ALL)
  private List<MealPlanRecipe> recipes;

  public MealPlan(int id, CalendarWeek week,
      List<MealPlanRecipe> recipes) {
    this.id = id;
    this.week = week;
    this.recipes = recipes;
  }

  public MealPlan() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MealPlan mealPlan = (MealPlan) o;
    return id == mealPlan.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public CalendarWeek getWeek() {
    return week;
  }

  public void setWeek(CalendarWeek week) {
    this.week = week;
  }

  public List<MealPlanRecipe> getRecipes() {
    return recipes;
  }

  public void setRecipes(List<MealPlanRecipe> recipes) {
    this.recipes = recipes;
  }

  public void addRecipe(MealPlanRecipe mealPlanRecipe) {
    this.recipes.add(mealPlanRecipe);
    mealPlanRecipe.setMealPlan(this);
  }
}

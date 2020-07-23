package de.ludi.weekPlanerAPI.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@JsonIgnoreProperties("mealPlan")
@Entity
public class MealPlanRecipe {

  @Id
  @GeneratedValue
  @Column(name = "mealPlanRecipe_id")
  private int id;

  @ManyToOne(cascade = CascadeType.ALL)
  private Recipe recipe;

  @ManyToOne(cascade = CascadeType.ALL)
  private MealPlan mealPlan;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  @Temporal(TemporalType.DATE)
  private Date day;

  public MealPlanRecipe(int id, Recipe recipe, MealPlan mealPlan, Date day) {
    this.id = id;
    this.recipe = recipe;
    this.mealPlan = mealPlan;
    this.day = day;
  }

  public MealPlanRecipe() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MealPlanRecipe that = (MealPlanRecipe) o;
    return id == that.id;
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

  public Recipe getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }

  public MealPlan getMealPlan() {
    return mealPlan;
  }

  public void setMealPlan(MealPlan mealPlan) {
    this.mealPlan = mealPlan;
  }

  public Date getDay() {
    return day;
  }

  public void setDay(Date day) {
    this.day = day;
  }
}

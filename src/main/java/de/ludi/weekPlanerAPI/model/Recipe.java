package de.ludi.weekPlanerAPI.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Recipe {

  @Id
  @GeneratedValue
  @Column(name = "recipe_id")
  private int id;

  @Column(nullable = false)
  private String name;

  private String note;

  @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
  private List<RecipeProduct> ingredients;

  public Recipe() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Recipe recipe = (Recipe) o;
    return id == recipe.id;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public List<RecipeProduct> getIngredients() {
    return ingredients;
  }

  public void setIngredients(List<RecipeProduct> ingredients) {
    this.ingredients = ingredients;
  }

  public void addIngredient(RecipeProduct recipeProduct) {
    this.ingredients.add(recipeProduct);
    recipeProduct.setRecipe(this);
  }
}

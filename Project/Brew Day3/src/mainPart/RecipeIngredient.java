package mainPart;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Map;
import com.dataBase.*;

// The ingredient recorded for the recipe
public class RecipeIngredient extends Ingredient implements Serializable {
	// A map to store the recipe with ingredient name
	private Map<Recipe, String> recipes;
	// !!! Notcie adding l in the behind
	private static final long serialVersionUID = -9072006108773320079l;
											
	
	// A constructor for recipe of ingredient to get corresponding information for ingredient
	public RecipeIngredient(String ingredientName, double amount, String unit) {
		super(ingredientName, amount, unit);
	}
	// Add recipe for ingredient
	public void addRecipe(String ingredientNames, Recipe recipe) {
		// If the recipes is null
		if(recipes==null) {
			
		}
		// Check whether the recipe is existing before
		else if(!recipes.containsKey(recipe)) {
			// Put the recipe with ingredient name
			recipes.put(recipe,ingredientNames);
			// Adding the ingredient to the recipe itself
			recipe.addIngredient(ingredientNames, this);
		}
	}
}

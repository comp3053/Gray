package mainPart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataBase.DataBase;
import java.io.Serializable;

public class Recipe implements Serializable {
	// Basic information for the recipe
	private String recipeName;
	private double quantity;
	private String unit;
	// A map to store the ingredient name and ingredient object
	private Map<String, RecipeIngredient> ingredients = new LinkedHashMap ();
	private Map<Integer, Brew> brewStore = new LinkedHashMap ();
	private static final long serialVersionUID = 4242495531479186920l;
	
	
	// Constructor of the recipe
	public Recipe(String rName, int rQuantity, String rUnit) {
		this.recipeName=rName;
		this.quantity=rQuantity;
		this.unit=rUnit;
	}
	
	public Map<Integer, Brew> getBrewStore() {
		return brewStore;
	}

	public void setBrewStore(Map<Integer, Brew> brewStore) {
		this.brewStore = brewStore;
	}
	
	//!!! Adding brew times
	public void addBrew(int brewId, Brew brewTime) {
		if(brewStore==null) {
			
		}
		else if(!brewStore.containsKey(brewId)) {
			brewStore.put(brewId,brewTime);
			brewTime.addRecipe(brewId, this);
		}
		else {
			System.out.println("Unable to add the brew action");
		}
	}
	
	
	// Adding the ingredient for the recipe
	public void addIngredient(String ingredientNames, RecipeIngredient ingredientOfRecipe) {
		// If the ingredient of the recipe is null
		if(ingredients==null) {
			
		}
		// Decide whether the ingredient is in the recipe before
		else if(!ingredients.containsKey(ingredientNames)) {
			ingredients.put(ingredientNames,ingredientOfRecipe);
			ingredientOfRecipe.addRecipe(ingredientNames,this);
			DataBase.addRecipeIngredient(this,ingredientOfRecipe,ingredients);
		}
		else {
			System.out.println("Unable to add the ingredient");
		}
	}
	
	
	public void setIngredients(Map<String, RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	@SuppressWarnings("unlikely-arg-type")
	// Only able to do the convert for 'ml', 'l' for recipe and 'kg', 'g' for ingredient.
	public Map convertValue(String recipeName,double convertAmount,String convertUnit, String ingredientUnit) {
		// A string for output information
		String info="";
		
		Map<String, Double> ingredientForConsuming = new LinkedHashMap ();
		
		// If there is not ingredient in the recipe, then do nothing
		if(ingredients==null) {
			System.out.println("Unable to convert, no ingredient in the recipe.");
			info+="Unable to convert, no ingredient in the recipe.";
			return ingredientForConsuming;
		}
		
		else {
			// Calculate the converting proportion
			double proportion;
			// Convert for the quantity proportion
			// The unit for the recipe is equal
			if(this.unit.equals(convertUnit)) {
				proportion = convertAmount/this.quantity;
			}
			// The unit for the recipe is not equal
			else {
				// Converting to 'ml' for the 'l' batch size
				if(this.unit.equals("ml")) {
					proportion = (convertAmount*1000)/this.quantity;
				}
				// Converting to 'l' for the 'ml' batch size
				else {
					proportion = (convertAmount/1000)/this.quantity;
				}
			}
			
			// Start converting
			for (RecipeIngredient value : ingredients.values()) {
				// Judge the unit for the ingredient in the recipe and the user input one
				// Two unit are equal
				 if(value.getUnit().equals(ingredientUnit)) {
					 System.out.print(value.getIngredientName()+" needs "+value.getAmount()*proportion+ingredientUnit+". ");
					 
					 ingredientForConsuming.put(value.getIngredientName(), value.getAmount()*proportion);
					 //info+=value.getIngredientName()+" needs "+value.getAmount()*proportion+ingredientUnit+". ";
				 }
				 // Two unit are not equal
				 else {
					 // Times the proportion with the unit converting value
					 if(ingredients.equals("g")) {
						 System.out.print(value.getIngredientName()+" needs "+value.getAmount()*proportion*1000+ingredientUnit+". ");
						 ingredientForConsuming.put(value.getIngredientName(), value.getAmount()*proportion*1000);
						 //info+=value.getIngredientName()+" needs "+value.getAmount()*proportion*1000+ingredientUnit+". ";
					 }
					 else {
						 System.out.print(value.getIngredientName()+" needs "+value.getAmount()*proportion/1000+ingredientUnit+". "); 
						 ingredientForConsuming.put(value.getIngredientName(), value.getAmount()*proportion/1000);
						 //info+=value.getIngredientName()+" needs "+value.getAmount()*proportion/1000+ingredientUnit+". ";
					 }
				 }
			} 
		}
		// Output the information message for the converted ingredient
		//return info;
		return ingredientForConsuming;
	}
	

	// Get the ingredient list of the recipe
	public Map getIngredients() {
		return ingredients;
	}
	
	public String getRecipeName() {
		return this.recipeName;
	}
	
	public void setRecipeName(String newName) {
		this.recipeName = newName;
	}
	
	
	public double getQuantity() {
		return this.quantity;
	}
	
	
	public void setQuantity(double newQuantity) {
		this.quantity= newQuantity;
	}
		
	public String getUnit() {
		return this.unit;
	}	
	
	public void setUnit(String newUnit) {
		this.unit= newUnit;
	}
	
	
}
	
	
	
	

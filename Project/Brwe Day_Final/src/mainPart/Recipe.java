package mainPart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.dataBase.DataBase;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

public class Recipe implements Serializable {
	// Basic information for the recipe
	private String recipeName;
	private double quantity;
	private String unit;
	// A map to store the ingredient name and ingredient object
	private Map<String, RecipeIngredient> ingredients = new LinkedHashMap ();
	private static final long serialVersionUID = 4242495531479186920l;
	
	// Constructor of the recipe
	public Recipe(String rName, double rQuantity, String rUnit) {
		this.recipeName=rName;
		this.quantity=rQuantity;
		this.unit=rUnit;
	}
	
	public Recipe() {
		// TODO Auto-generated constructor stub
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
			// If the ingreident is not in the recipe, then add it to the recipe
			DataBase.addRecipeIngredient(this,ingredientOfRecipe);
		}
		else {
			System.out.println("Unable to add the ingredient");
		}
	}
	
	// Change the ingredient in recipe
	public void setIngredients(Map<String, RecipeIngredient> ingredients) {
		this.ingredients = ingredients;
	}

	// Get the ingredient list of the recipe
	public Map getIngredients() {
		return ingredients;
	}
	
	// Get specific recipe
	public Recipe getRecipe(String rName) {
		return DataBase.getRecipe(rName);
	}
	
	// Get all recipe name
	public ArrayList<String> getAllRecipeName() throws SQLException{
		return DataBase.getAllRecipe();
	}
	// Updating the recipe with many information of that recipe input
	public void recipeForManyUpdating(String recipeName, String literT, String maltT,String hopT,String yeastT,String sugarT,String additiveT) {
		Recipe r = new Recipe();
		r = this.getRecipe(recipeName);
		Map<String,RecipeIngredient> ingredientList = r.getIngredients();
		// Store the new adding ingredient
		ArrayList<RecipeIngredient> newAddIngredient = new ArrayList<RecipeIngredient>();
		// Set the liter for the recipe
		if(literT.length()!=0) {
			double liter;
			liter = Double.parseDouble(literT);
			r.setQuantity(liter);
		}
		// Set the malt for the recipe
		if(maltT.length()!=0) {
			double malt;
			malt = Double.parseDouble(maltT);
			if(ingredientList.containsKey("malts")) {
				ingredientList.get("malts").setAmount(malt);
				r.updateRecipeIngredient(recipeName, "malts", malt);
			}
			else {
				RecipeIngredient ri = new RecipeIngredient("malts",malt,"g");
				newAddIngredient.add(ri);
			}
		}
		// Set the hop for the recipe
		if(hopT.length()!=0) {
			double hop;
			hop = Double.parseDouble(hopT);
			if(ingredientList.containsKey("hops")) {
				ingredientList.get("hops").setAmount(hop);
				r.updateRecipeIngredient(recipeName,"hops",hop);
			}
			else {
				RecipeIngredient ri = new RecipeIngredient("hops",hop,"g");
				newAddIngredient.add(ri);
			}
		}
		// Set the yeast for recipe
		if(yeastT.length()!=0) {
			double yeast;
			// Convert the string input into the double form
			yeast = Double.parseDouble(yeastT);
			if(ingredientList.containsKey("yeasts")) {
				ingredientList.get("yeasts").setAmount(yeast);
				r.updateRecipeIngredient(recipeName,"yeasts",yeast);
			}
			else {
				RecipeIngredient ri = new RecipeIngredient("yeasts",yeast,"g");
				newAddIngredient.add(ri);
			}
		}
		if(sugarT.length()!=0) {
			double sugar;
			sugar = Double.parseDouble(sugarT);
			if(ingredientList.containsKey("sugars")) {
				ingredientList.get("sugars").setAmount(sugar);
				r.updateRecipeIngredient(recipeName,"sugars",sugar);
			}
			// Ingredient is not in the recipe
			else {
				RecipeIngredient ri = new RecipeIngredient("sugars",sugar,"g");
				newAddIngredient.add(ri);
			}
		}
		if(additiveT.length()!=0) {
			double additives;
			additives = Double.parseDouble(additiveT);
			if(ingredientList.containsKey("additives")) {
				ingredientList.get("additives").setAmount(additives);
				r.updateRecipeIngredient(recipeName,"additives",additives);
			}
			else {
				RecipeIngredient ri = new RecipeIngredient("additives",additives,"g");
				// Adding ingredient to the new ingredient list
				newAddIngredient.add(ri);
			}
		}
		// Updating the ingredient list
		r.setIngredients(ingredientList);
		// Updating the recipe
		r.updateRecipe(r);
		r = r.getRecipe(recipeName);
		// Adding the new ingredient for the recipe
		for(int i=0;i<newAddIngredient.size();i++)
			r.addIngredient(newAddIngredient.get(i).getIngredientName(), newAddIngredient.get(i));		
		r.updateRecipe(r);
	}
	
	// Checking for single adding
	// 1 for adding model, 0 for minus model
	public int checkStringInput(String inputSize) {
			// Input nothing
			if(inputSize.length()==0)
				return 0;
			// Input with problem
			else {
				Equipment equip = new Equipment();
				int check = equip.checkValidNumber(inputSize);
				if(check==1)
					return 1;
				if(check==2)
					return 2;
				if(check==3)
					return 3;
			}
			// Input with no problem
			return 2;
		}
	
	// Get satisfied recipe in certain batch size
	public ArrayList<String> calculateSatifiedRecipe( Map<String, StorageIngredient> allStorageIngredient, double batchSize, int model) throws ClassNotFoundException, SQLException, IOException {
		 return DataBase.calculateSatifiedRecipe(allStorageIngredient, batchSize,model);
	}
	
	// Update specific recipe ingredient
	public void updateRecipeIngredient(String recipeName, String ingredientName, double newAmount) {
		DataBase.updateRecipeIngredient(recipeName,ingredientName,newAmount);
	}
	
	// Recipe missing calculation
	public Map<String,Double> recipeMissingCalculation(String recipeName,double batchSize) throws SQLException{
		return DataBase.recipeMissingCalculation(recipeName, batchSize);
	}
	
	
	// Deleting recipe
	public void deletingRecipe(String recipeName) {
		DataBase.deleteRecipe(recipeName);
	}
	
	// Convert a value for recipe
	@SuppressWarnings("unlikely-arg-type")
	public Map convertValue(String recipeName,double convertAmount,String convertUnit, String ingredientUnit) {
		// Output the converted map, with ingredient name and their amount
		Map<String, Double> ingredientForConsuming = new LinkedHashMap ();
		
		// If there is not ingredient in the recipe, then do nothing
		if(ingredients==null) {
			System.out.println("Unable to convert, no ingredient in the recipe.");
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
	
	// Recipe for bunch of adding
	public void recipeForManyAdding(String recipeName, String literT, String maltT,String hopT,String yeastT,String sugarT,String additiveT) {
		// Default value
		double liter=0,malt=0,hop=0,yeast=0,sugar=0,additive=0;
		// Convert the liter into double form
		liter= Double.parseDouble(literT);
		Recipe r = new Recipe(recipeName,liter,"ml");
		// Adding new recipe
		DataBase.addRecipe(r);
		// Below is to convert the amount into the double form and add it into the recipe
		if(maltT.length()!=0) {
			malt= Double.parseDouble(maltT);
			RecipeIngredient r1 = new RecipeIngredient("malts",malt,"g");
			r.addIngredient("malts", r1);
		}
		if(hopT.length()!=0) {
			hop= Double.parseDouble(hopT);
			RecipeIngredient r2 = new RecipeIngredient("hops",hop,"g");
			r.addIngredient("hops", r2);
		}
		if(yeastT.length()!=0) {
			yeast= Double.parseDouble(yeastT);
			RecipeIngredient r3 = new RecipeIngredient("yeasts",yeast,"g");
			r.addIngredient("yeasts", r3);
		}
		if(sugarT.length()!=0) {
			sugar= Double.parseDouble(sugarT);
			RecipeIngredient r4 = new RecipeIngredient("sugars",sugar,"g");
			r.addIngredient("sugars", r4);
		}
		if(additiveT.length()!=0) {
			additive= Double.parseDouble(additiveT);
			RecipeIngredient r5 = new RecipeIngredient("additives",additive,"g");
			r.addIngredient("additives", r5);
		}
	}
	
	// Update recipe
	public void updateRecipe(Recipe r) {
		DataBase.updateRecipe(r);
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
	
	
	
	

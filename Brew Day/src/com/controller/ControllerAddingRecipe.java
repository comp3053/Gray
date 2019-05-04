package com.controller;
import mainPart.*;
import com.dataBase.DataBase;
import mainPart.Recipe;
import mainPart.RecipeIngredient;

public class ControllerAddingRecipe {

	public ControllerAddingRecipe() {
		
	}
	
	public void conAddingRecipe(String RecipeName, double liter, double malt, double hop, double yeast, double sugar, double additives) {
		
		Recipe r = new Recipe(RecipeName,liter,"ml");
		DataBase.addRecipe(r);
		if(malt>0) {
			RecipeIngredient r1 = new RecipeIngredient("malts",malt,"g");
			r.addIngredient("malts", r1);
		}
		if(hop>0) {
			RecipeIngredient r2 = new RecipeIngredient("hops",hop,"g");
			r.addIngredient("hops", r2);
		}
		if(yeast>0) {
			RecipeIngredient r3 = new RecipeIngredient("yeasts",yeast,"g");
			r.addIngredient("yeasts", r3);
		}
		if(sugar>0) {
			RecipeIngredient r4 = new RecipeIngredient("sugars",sugar,"g");
			r.addIngredient("sugars", r4);
		}
		if(additives>0) {
			RecipeIngredient r5 = new RecipeIngredient("additives",additives,"g");
			r.addIngredient("additives", r5);
		}
		
	}
}

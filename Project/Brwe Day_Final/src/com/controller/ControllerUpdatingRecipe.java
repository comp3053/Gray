package com.controller;
import java.util.ArrayList;
import java.util.Map;
import com.dataBase.DataBase;
import mainPart.Recipe;
import mainPart.RecipeIngredient;

public class ControllerUpdatingRecipe {
	Recipe r;
	public ControllerUpdatingRecipe() {
		
		}
	// This is the method for controller to updating recipe
	public String conUpdatingRecipe(String recipeName,String literT, String maltT,String hopT,String yeastT,String sugarT,String additiveT) {
		//set new recipe
		r = new Recipe();
		int check0 = r.checkStringInput(literT);
		int check1 = r.checkStringInput(maltT);
		int check2 = r.checkStringInput(hopT);
		int check3 = r.checkStringInput(yeastT);
		int check4 = r.checkStringInput(sugarT);
		int check5 = r.checkStringInput(additiveT);
		// Check the status and return correspoinding information
		
		if(check0==3 || check1==3 || check2==3 || check3==3 || check4==3 || check5==3 ) {
			return "invalid";
		}
		else if(check0==1 || check1==1 || check2==1 || check3==1 || check4==1 || check5==1 ) {
			return "less than 0";
		}
		else {
			System.out.println("here");
			r.recipeForManyUpdating(recipeName,literT, maltT, hopT, yeastT, sugarT, additiveT);
			return "good";
		}
	}
}

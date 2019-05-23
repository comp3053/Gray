package com.controller;
import java.sql.SQLException;

import mainPart.*;
public class ControllerForBrewRecipe {
	private Brew b;
	public ControllerForBrewRecipe() {
		// This is the method for controller to brew a recipe
	}
	public void conForBrewRecipe(String recipeName, double batchSize,String batchUnit,String ingredientUnit) {
		try {
			//set new brew
			b = new Brew(recipeName, batchSize,batchUnit,ingredientUnit);
			b.implement(recipeName, batchSize);
			//try to implement the brewing 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

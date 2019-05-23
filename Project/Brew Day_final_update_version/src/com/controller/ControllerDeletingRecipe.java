package com.controller;
import mainPart.Recipe;

public class ControllerDeletingRecipe {
	private Recipe recipe;
	public ControllerDeletingRecipe() {
	}
	// This is the method for controller to deleting recipe
	public void ConDeletingRecipe(String recipeName) {
		//set new recipe
		recipe = new Recipe();
		recipe.deletingRecipe(recipeName);
	}
}

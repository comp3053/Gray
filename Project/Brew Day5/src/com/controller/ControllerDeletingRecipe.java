package com.controller;
import mainPart.Recipe;

public class ControllerDeletingRecipe {
	private Recipe recipe;
	public ControllerDeletingRecipe() {
	}
	
	public void ConDeletingRecipe(String recipeName) {
		recipe = new Recipe();
		recipe.deletingRecipe(recipeName);
	}
}

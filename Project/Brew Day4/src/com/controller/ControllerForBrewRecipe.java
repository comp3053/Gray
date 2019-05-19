package com.controller;
import java.sql.SQLException;

import com.exception.LargerThanEquip;

import mainPart.*;
public class ControllerForBrewRecipe {
	private Brew b;
	public ControllerForBrewRecipe() {
		
	}
	public void conForBrewRecipe(String recipeName, double batchSize,String batchUnit,String ingredientUnit) {
		try {
			b = new Brew(recipeName, batchSize,batchUnit,ingredientUnit);
			b.implement(recipeName, batchSize);
		} catch (LargerThanEquip e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

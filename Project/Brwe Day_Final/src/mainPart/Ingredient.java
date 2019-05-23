package mainPart;

import java.io.Serializable;

// An ingredient class set as parent class
public class Ingredient implements Serializable{
	// Basis information for ingredient
	private String ingredientName;
	private double amount;
	private String unit;
	private static final long serialVersionUID = -1201981417057638901l;
	
	// The constructor to get the information for the ingredient
	public Ingredient(String ingredientName, double amount, String unit) {
		this.ingredientName = ingredientName;
		this.amount = amount;
		this.unit = unit;
	}
	public Ingredient() {
		// TODO Auto-generated constructor stub
	}
	// Get the name of ingredient
	public String getIngredientName() {
		return this.ingredientName;
	}
	// Set ingredient name
	public void setIngredientName(String newName) {
		this.ingredientName = newName;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double newAmount) {
		this.amount = newAmount;
	}
	
	// Update the amount for the ingredient - Adding and subtracting
	public void updateAmount(double newAmount) {
		this.amount+=newAmount;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public void setUnit(String newUnit) {
		this.unit = newUnit;
	}
	
}

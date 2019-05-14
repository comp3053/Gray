package mainPart;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.exception.LargerThanEquip;

public class Brew {
	static int index = 0;
	private double batchSize;
	private String batchUnit;
	private String ingredientUnit;
	private Equipment equipmentUse;
	private Note note;
	private int order;
	private java.sql.Date date;
	private java.sql.Time time;
	
	private Map<Recipe, Integer> brewRecipe;
	private Map<String, StorageIngredient> consumIngredienet;
	
	
	
	//!!! Constructor  !!!!
	public Brew(double inputSize,String inputUnit,String ingredientUnit,Recipe recipe,String reicpeName, Equipment equUse, ArrayList<StorageIngredient> ingredientArray) throws LargerThanEquip {   
 		if(equUse.getCapUnit().equals(inputUnit)) {
 			if(equUse.getCapacity()<inputSize)
 				throw new LargerThanEquip();
 		}
 		else if(equUse.getCapUnit().equals("l")) {
 			if(equUse.getCapacity()<(inputSize*1000))
 				throw new LargerThanEquip();
 		}
 		else {
 			if(equUse.getCapacity()<(inputSize/1000))
 				throw new LargerThanEquip();
 		}
 		this.equipmentUse = equUse;	
		this.order = index++;
		this.batchSize = inputSize;
		this.batchUnit = inputUnit;
		this.ingredientUnit = ingredientUnit;
	
		date = new java.sql.Date(System.currentTimeMillis());
		time = new java.sql.Time(System.currentTimeMillis());
		this.implement(recipe,reicpeName,ingredientArray);
	}

	//!!! Adding brew times
	public void addRecipe(int brewId, Recipe brewOfRecipe) {
		if(brewRecipe==null) {

		}
		else if(!brewRecipe.containsKey(brewId)) {
			brewRecipe.put(brewOfRecipe,brewId);
			brewOfRecipe.addBrew(brewId, this);
		}
		else {
			System.out.println("Unable to add the recipe action");
		}
	}
	
	//!!! Adding ingredient consuming
	public void addStorageIngredient(String storageIngredientName, StorageIngredient storageIngredient) {
		if(consumIngredienet==null) {

		}
		else if(!consumIngredienet.containsKey(storageIngredientName)) {
			consumIngredienet.put(storageIngredientName,storageIngredient);
			storageIngredient.addBrew(storageIngredientName, this);
		}
		else {
			System.out.println("Unable to add the recipe action");
		}
	}
	
	// !!! Implementation !!!
	public void implement(Recipe recipe,String recipeName,ArrayList<StorageIngredient> ingredientArray) {
		Map<String, Double> ingredientForConsuming = new LinkedHashMap ();
		ingredientForConsuming = recipe.convertValue(recipeName, this.batchSize, this.batchUnit, this.ingredientUnit);
		System.out.println(ingredientForConsuming);
		for(int i=0;i<ingredientArray.size();i++) {
			if(ingredientForConsuming.containsKey(ingredientArray.get(i).getIngredientName())) {
				if(ingredientArray.get(i).getUnit().equals(this.ingredientUnit)) {
					ingredientArray.get(i).subtractAmount(ingredientForConsuming.get(ingredientArray.get(i).getIngredientName()));
				
				}
				else if(ingredientArray.get(i).getUnit().equals("kg"))
					ingredientArray.get(i).subtractAmount(ingredientForConsuming.get(ingredientArray.get(i).getIngredientName())/1000);
				else
					ingredientArray.get(i).subtractAmount(ingredientForConsuming.get(ingredientArray.get(i).getIngredientName())*1000);
			}
		}
	}
	
	

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		Brew.index = index;
	}

	public void addNote(Note inputNote) {
		this.note = inputNote;
		note.addBrew(this);
	}
	
	public String getBatchUnit() {
		return batchUnit;
	}

	public void setBatchUnit(String batchUnit) {
		this.batchUnit = batchUnit;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

	public void setTime(java.sql.Time time) {
		this.time = time;
	}

	public double getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(double batchSize) {
		this.batchSize = batchSize;
	}

	public java.sql.Date getDate() {
		return date;
	}

	public java.sql.Time getTime() {
		return time;
	}

}

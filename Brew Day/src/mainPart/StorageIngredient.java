package mainPart;
import java.util.Map;

public class StorageIngredient extends Ingredient {
	
	private Map<Brew, String> consumeBrew;
	
	public StorageIngredient(String ingredientName, int amount, String unit) {
		super(ingredientName, amount, unit);
	}
	
	public void addBrew(String storageIngredientName, Brew brew) {
		if(consumeBrew==null) {

		}
		else if(!consumeBrew.containsKey(brew)) {
			consumeBrew.put(brew,storageIngredientName);
			brew.addStorageIngredient(storageIngredientName, this);
		}
		else {
			System.out.println("Unable to add the recipe action");
		}
	}
	
	public void addAmount(double addNum) {
		this.updateAmount(addNum);
	}
	
	public void subtractAmount(double subNum) {
		this.updateAmount(-subNum);
	}
}

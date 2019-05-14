package mainPart;
import com.dataBase.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.exception.LargerThanEquip;

public class main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Recipe blackBeer = new Recipe("black beer",500,"ml");
		//Recipe whiteBeer = new Recipe("white beer",300,"ml");
		//DataBase.addRecipe(whiteBeer);
		Recipe Tim = DataBase.getRecipe("Tim");
		System.out.println(Tim.getQuantity());
//		blackBeer.setQuantity(300);
		
		//System.out.println(blackBeer.getIngredients());
		
		RecipeIngredient malts = new RecipeIngredient("malts",10,"g");
		RecipeIngredient sugars = new RecipeIngredient("sugars",20,"g");
		RecipeIngredient yeasts = new RecipeIngredient("yeasts",20,"g");

		//blackBeer.addIngredient(malts.getIngredientName(), malts);
		
		StorageIngredient malts1 = new StorageIngredient("malts",10,"kg");
		
		Note n = new Note("Good Record");
		
		Equipment e = new Equipment(100,"ml" );
		
		try {
			System.out.println(DataBase.getAllStorageIngredient());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
//		// Add ingredient to recipe
//		blackBeer.addIngredient(malts.getIngredientName(), malts);
//		blackBeer.addIngredient(sugars.getIngredientName(), sugars);
//		blackBeer.addIngredient(yeasts.getIngredientName(), yeasts);
//		// Converting value for new input
//		blackBeer.convertValue("black beer", 0.5, "l", "g");
//		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
//		java.sql.Time time = new java.sql.Time(System.currentTimeMillis());
//		System.out.println("\n"+date);
//		System.out.println(time);
//		// Input storage ingredient
//		StorageIngredient malts1 = new StorageIngredient("malts",10,"kg");
//		StorageIngredient sugars1 = new StorageIngredient("sugars",20,"kg");
//		StorageIngredient yeasts1 = new StorageIngredient("yeasts",20,"kg");
//		// Creating an array to store the ingredient
//		ArrayList ingredientArray = new ArrayList<StorageIngredient>();
//		ingredientArray.add(malts1);
//		ingredientArray.add(sugars1);
//		ingredientArray.add(yeasts1);
//		
//		Equipment e1 = new Equipment(0.5,"l" );
//		// Brewing a beer
//		try {
//			Brew b1 = new Brew(1,"l","g",blackBeer,"black beer",e1,ingredientArray);
//		}
//		catch(LargerThanEquip l){
//			System.out.println("Invalid input for batch size.");
//		}
//		
//		try {
//			Brew b1 = new Brew(0.5,"l","g",blackBeer,"black beer",e1,ingredientArray);
//			Note n1 = new Note("It tastes good, but it still need some more food!");
//			b1.addNote(n1);
//			System.out.println("Good!");
//		} catch (LargerThanEquip e) {
//			System.out.println("Bad!");
//		}
//		System.out.println(((Ingredient) ingredientArray.get(0)).getAmount());
//		System.out.println(((Ingredient) ingredientArray.get(1)).getAmount());
//		System.out.println(((Ingredient) ingredientArray.get(2)).getAmount());
//		
	}

}

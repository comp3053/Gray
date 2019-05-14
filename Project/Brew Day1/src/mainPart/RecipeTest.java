package mainPart;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

// Test for the function in recipe
public class RecipeTest {
	// Creating a recipe
	private Recipe recipe = new Recipe("black beer",500,"ml");
	// Creating a bunch of ingredient
	RecipeIngredient malts = new RecipeIngredient("malts",10,"g");
	RecipeIngredient sugars = new RecipeIngredient("sugars",20,"g");
	RecipeIngredient yeasts = new RecipeIngredient("yeasts",20,"g");

	@Before
	public void setUp() throws Exception {
		//recipe.getIngredients().clear();
	}


	// Test whether the ingredient has been successfully added to the recipe
	@Test
	public void testAddIngredient() {
		// Adding the ingredient to the recipe
		recipe.addIngredient(malts.getIngredientName(), malts);
		recipe.addIngredient(sugars.getIngredientName(), sugars);
		// Test whether the object is equal to the added one
		assertEquals(recipe.getIngredients().get("malts"),malts);
		assertEquals(recipe.getIngredients().get("sugars"),sugars);
	}

	// Only able to do the convert for 'ml', 'l' for recipe and 'kg', 'g' for ingredient.
	@Test
	public void testConvertValue() {
		// Adding the ingredient to the recipe
		recipe.addIngredient(malts.getIngredientName(), malts);
		recipe.addIngredient(sugars.getIngredientName(), sugars);
		recipe.addIngredient(yeasts.getIngredientName(), yeasts);

		// Check the converting output for the ingredient in the recipe
		assertEquals("malts needs 20.0g. sugars needs 40.0g. yeasts needs 40.0g. ",recipe.convertValue("black beer", 1000, "ml", "g"));
		assertEquals("malts needs 10.0g. sugars needs 20.0g. yeasts needs 20.0g. ",recipe.convertValue("black beer", 0.5, "l", "g"));
		assertEquals("malts needs 0.02kg. sugars needs 0.04kg. yeasts needs 0.04kg. ",recipe.convertValue("black beer", 1, "l", "kg"));
	}



	@Test
	public void testGetRecipeName() {
		assertEquals(recipe.getRecipeName(),"black beer");
	}

	@Test
	public void testSetRecipeName() {
		recipe.setRecipeName("white beer");
		assertEquals(recipe.getRecipeName(),"white beer");
	}


	@Test
	public void testGetUnit() {
		assertEquals(recipe.getUnit(),"ml");
	}

	@Test
	public void testSetUnit() {
		recipe.setUnit("l");
		assertEquals(recipe.getUnit(),"l");
	}

}

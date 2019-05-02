package com.dataBase;
import java.sql.*;
import java.util.Map;

import mainPart.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;


public class DataBase {
	
	// Connect to database
	public static class DBConnection{
		// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://localhost/brew?serverTimezone = GMT";
		// Database credentials
		static final String USER = "root";
		static final String PASS = "123";
		static Connection conn = null;
		
		static {
			// ¼ÓÔØÇý¶¯
			try {
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static Connection getConnection() {
			return conn;
		}
	}
	
	// Adding ingredient to recipe
	public static void addRecipeIngredient(Recipe recipe,RecipeIngredient ingredientOfRecipe,Map<String, RecipeIngredient> ingredients) {
		
		String recipeName = recipe.getRecipeName();
		String ingredientName = ingredientOfRecipe.getIngredientName();
		double ingredientAmount = ingredientOfRecipe.getAmount();
		String ingredientUnit = ingredientOfRecipe.getUnit();
		String sql = "insert into ingredient_recipe(recipe_name,ingredient_name,ingredient_amount,ingredient_unit)values(?,?,?,?)";
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recipeName);
			ps.setObject(2,ingredientName);
			ps.setObject(3, ingredientAmount);
			ps.setObject(4, ingredientUnit);
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully adding ingredient in recipe!\n");
			DataBase.updateRecipe(recipe);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Adding recipe
	public static int addRecipe(Recipe recipe) {
		
		String recipeName = recipe.getRecipeName();
		double recipeQuantity = recipe.getQuantity();
		String recipeUnit = recipe.getUnit();
		String sql = "insert into recipe(name,quantity,unit,value)values(?,?,?,?)";
		Connection conn = DBConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recipeName);
			ps.setObject(2,recipeQuantity);
			ps.setObject(3, recipeUnit);
			ps.setObject(4, recipe);
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully adding recipe!\n");
			return result;
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// Updating recipe
	public static int updateRecipe(Recipe recipe) {
		String recipeName = recipe.getRecipeName();
		double recipeQuantity = recipe.getQuantity();
		String recipeUnit = recipe.getUnit();
		String sql = "UPDATE recipe SET name=?,quantity=?,unit=?,value=? WHERE name=?";
		Connection conn = DBConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recipeName);
			ps.setObject(2,recipeQuantity);
			ps.setObject(3, recipeUnit);
			ps.setObject(4, recipe);
			ps.setString(5, recipeName);
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully update for recipe!\n");
			return result;
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	// Getting recipe
	public static Recipe getRecipe(String recipeName) {
		
		Connection conn = DBConnection.getConnection();
		String sql = "select * from recipe where name=?";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, recipeName);
			ResultSet rs = ps.executeQuery();
 
			while (rs.next()) {
				ObjectInputStream ois = new ObjectInputStream(
						rs.getBinaryStream("value"));
				Recipe recipe = (Recipe) ois.readObject();
				System.out.println("getObj-->" + recipe.toString());
				return recipe;
			}
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
 
	}

	// Adding storage to database
	public static int updateStorageIngredient(StorageIngredient storageIngredient) {
		
		String igredientName = storageIngredient.getIngredientName();
		double ingredientAmount = storageIngredient.getAmount();
		String ingredientUnit = storageIngredient.getUnit();
		String sql = "update ingredient_storage set ingredient_amount=?,ingredient_unit=? where ingredient_name=?";
		Connection conn = DBConnection.getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, ingredientAmount);
			ps.setObject(2,ingredientUnit);
			ps.setString(3, igredientName);
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully updating for storage ingredient!\n");
			return result;
 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}


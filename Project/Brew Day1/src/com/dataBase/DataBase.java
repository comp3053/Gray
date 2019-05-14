package com.dataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
			// 加载驱动
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
	public static void addRecipeIngredient(Recipe recipe,RecipeIngredient ingredientOfRecipe) {
		
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
	
	// Updating ingredient storage to database
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

	public static Map<String, StorageIngredient> getAllStorageIngredient() throws SQLException {
		Map<String, StorageIngredient> AllStroageIngredient = new LinkedHashMap ();
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from ingredient_storage");
		// Get all information from ingredient_storage ingredient
		while (rs.next()) {
			String ingredientName = rs.getString("ingredient_name");
			double ingredientAmount = rs.getDouble("ingredient_amount");
			String ingredientUnit = rs.getString("ingredient_unit");
			StorageIngredient s = new StorageIngredient(ingredientName,ingredientAmount,ingredientUnit);
			AllStroageIngredient.put(ingredientName, s);
		}
		return AllStroageIngredient;
	}
	
	// Check for whether the batch size fits the input
	public static boolean checkBatchSize(double batchSize) throws SQLException {
		
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select capacity from equipment");

		while (rs.next()) {//下一行
			int capacity = rs.getInt("capacity");//获取id这一列
			if(capacity>=batchSize)
				return true;
		}
		return false;
	}
	
	// Check the recipe with satisfied recipe capacity
	public static ArrayList<String> calculateSatifiedRecipe(Map<String, StorageIngredient> AllStroageIngredient, double batchsize) throws SQLException, IOException, ClassNotFoundException {
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select value from recipe");
		ArrayList<String> recipeName = new ArrayList();
		// Get all information from ingredient_storage ingredient
		while (rs.next()) {
			ObjectInputStream ois = new ObjectInputStream(
			rs.getBinaryStream("value"));
			Recipe recipe = (Recipe) ois.readObject();
			if(recipeIngredientSatisfaction(recipe,AllStroageIngredient,batchsize)==true)
				recipeName.add(recipe.getRecipeName());
		}
		return recipeName;
	}
	
	public static boolean recipeIngredientSatisfaction(Recipe recipe, Map<String, StorageIngredient> AllStroageIngredient, double batchsize) {
		Map<String,Double>recipeMap = recipe.convertValue(recipe.getRecipeName(), batchsize, "ml", "g");
		
		for(String key:recipeMap.keySet()) {
			double storageValue = AllStroageIngredient.get(key).getAmount();
			double recipeValue = recipeMap.get(key);
			if(recipeValue>storageValue) {
				return false;
			}
		}
		return true;
	}

}


package com.dataBase;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import mainPart.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
// Database class for handling the connection from database to main class
public class DataBase {

	// Method for connecting to database
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

	// Get last brewing, which is useful for the write note setting after brewing
	public static Brew getLastBrewing() throws SQLException, ParseException {
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from brew order by brew_id DESC limit 1;");
		Brew b = null ;
		// Get all information from the brew experience
		while (rs.next()) {
			int index = rs.getInt("brew_id");
			double batchSize = rs.getDouble("batch_size");
			String batchUnit = rs.getString("batch_unit");
			String recipeName = rs.getString("recipe_name");
			String dateS = rs.getString("date");
			String timeS = rs.getString("time");
			b = new Brew(recipeName,batchSize,batchUnit,"g");
			java.util.Date date1 = null ;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
			date1 = sdf.parse(dateS) ;
			java.sql.Date sqlDate = new java.sql.Date(date1.getTime()) ;
			java.util.Date time1 = null ;
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm-ss") ;
			time1 = sdf1.parse(timeS) ;
			java.sql.Time sqlTime = new java.sql.Time(time1.getTime()) ;
			b.setDate(sqlDate);
			b.setTime(sqlTime);
			b.setBatchSize(batchSize);
			b.setIndex(index);
		}
		return b;
	}
	
	// Recording brew time after the user finished the brewing
	public static void brewRecording(double batchSize, String batchUnit, String recipeName, java.util.Date date, Time time) {
		String sql = "insert into brew(batch_size,batch_unit,recipe_name,date,time)values(?,?,?,?,?)";
		Connection conn = DBConnection.getConnection();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("HH-mm-ss");
        String dateSet = df.format(date);
        String timeSet = df1.format(time);
        // Parameter setting for the brewing recording
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, batchSize);
			ps.setObject(2,batchUnit);
			ps.setObject(3, recipeName);
			ps.setObject(4, dateSet);
			ps.setObject(5, timeSet);
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully adding brew time!\n");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Write note for brew
	public static void writeNoteForBrew(int index, String contnet, java.util.Date date, Time time) {
		// Insert the note information into database
		String sql = "insert into note(brew_id,content,date,time)values(?,?,?,?)";
		Connection conn = DBConnection.getConnection();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("HH-mm-ss");
        String dateSet = df.format(date);
        String timeSet = df1.format(time);
    	try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, index);
			ps.setObject(2,contnet);
			ps.setObject(3, dateSet);
			ps.setObject(4, timeSet);
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully adding note!\n");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Update note information
	public static void updateNoteInfor(Note note) {
		String sql = "update note set content=?, date=?, time=? where brew_id=?";
		Connection conn = DBConnection.getConnection();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat df1 = new SimpleDateFormat("HH-mm-ss");
        String dateSet = df.format(note.getDate());
        String timeSet = df1.format(note.getTime());
        // Parameter setting for note updating
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, note.getContent());
			ps.setObject(2,dateSet);
			ps.setObject(3, timeSet);
			ps.setObject(4, note.getBrewID());
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully updating note!\n");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// Get all brew information with no note written yet
	public static ArrayList<Brew> getAllBrewInformationWithoutNote() throws SQLException, ParseException {
		ArrayList<Brew> brewList = new 	ArrayList<Brew>();
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from brew where brew_id not in(select brew_id from note);");
		// Get all information from brew database class
		while (rs.next()) {
			int index = rs.getInt("brew_id");
			double batchSize = rs.getDouble("batch_size");
			String batchUnit = rs.getString("batch_unit");
			String recipeName = rs.getString("recipe_name");
			String dateS = rs.getString("date");
			String timeS = rs.getString("time");
			Brew b = new Brew(recipeName,batchSize,batchUnit,"g");
			java.util.Date date1 = null ;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
			date1 = sdf.parse(dateS) ;
			java.sql.Date sqlDate = new java.sql.Date(date1.getTime()) ;
			java.util.Date time1 = null ;
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm-ss") ;
			time1 = sdf1.parse(timeS) ;
			java.sql.Time sqlTime = new java.sql.Time(time1.getTime()) ;
			b.setDate(sqlDate);
			b.setTime(sqlTime);
			b.setBatchSize(batchSize);
			b.setIndex(index);
			brewList.add(b);
		}
		return brewList;
	}
	
	// Get specific note content
	public static Note getSpecificNote(int index) throws SQLException, ParseException {
		// Get the note content through the brewing index
		Connection conn = DBConnection.getConnection();
		String sql = "select * from note where brew_id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setLong(1, index);
		ResultSet rs = ps.executeQuery();
		String content=null;
		String date=null,time=null;
		while (rs.next()) {
			content = rs.getString("content");
			date = rs.getString("date");
			time = rs.getString("time");
		}		
		// Converting the time management
		java.util.Date date1 = null ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
		date1 = sdf.parse(date) ;
		java.sql.Date sqlDate = new java.sql.Date(date1.getTime()) ;
		java.util.Date time1 = null ;
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm-ss") ;
		time1 = sdf1.parse(time) ;
		java.sql.Time sqlTime = new java.sql.Time(time1.getTime()) ;
		Note note = new Note(content,index);
		note.setDate(sqlDate);
		note.setTime(sqlTime);
		return note;
	}
	
	// Get brew with note written
	public static ArrayList<Brew> getBrewWithNoteWritten() throws SQLException, ParseException {
		ArrayList<Brew> brewList = new 	ArrayList<Brew>();
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from brew where brew.brew_id = any(select brew_id from note)");
		// Get all information from ingredient_storage ingredient
		while (rs.next()) {
			int index = rs.getInt("brew_id");
			double batchSize = rs.getDouble("batch_size");
			String batchUnit = rs.getString("batch_unit");
			String recipeName = rs.getString("recipe_name");
			String dateS = rs.getString("date");
			String timeS = rs.getString("time");
			Brew b = new Brew(recipeName,batchSize,batchUnit,"g");
			java.util.Date date1 = null ;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
			date1 = sdf.parse(dateS) ;
			java.sql.Date sqlDate = new java.sql.Date(date1.getTime()) ;
			java.util.Date time1 = null ;
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH-mm-ss") ;
			time1 = sdf1.parse(timeS) ;
			java.sql.Time sqlTime = new java.sql.Time(time1.getTime()) ;
			b.setDate(sqlDate);
			b.setTime(sqlTime);
			b.setBatchSize(batchSize);
			b.setIndex(index);
			brewList.add(b);
		}
		return brewList;
	}
	
	// Adding ingredient to recipe
	public static void addRecipeIngredient(Recipe recipe,RecipeIngredient ingredientOfRecipe) {
		// Adding the reicpe to the ingredient
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

	// Adding recipe to database
	public static int addRecipe(Recipe recipe) {

		String recipeName = recipe.getRecipeName();
		double recipeQuantity = recipe.getQuantity();
		String recipeUnit = recipe.getUnit();
		// value is to the store the recipe object into the binary form
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

	// Deleting recipe from the database for recipe and ingredient_recipe tale
	public static void deleteRecipe(String recipeName) {
		String sql1 = "delete from recipe where name=?";
		String sql2 = "delete from ingredient_recipe where recipe_name=? ";
		Connection conn = DBConnection.getConnection();

		try {
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setString(1, recipeName);
			int result1 = ps1.executeUpdate();

			PreparedStatement ps2 = conn.prepareStatement(sql2);
			ps2.setString(1, recipeName);
			int result2 = ps2.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Updating recipe with current information in the recipe object
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

	// Getting recipe object with name input as parameter
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

	// Getting all recipe name
	public static ArrayList<String> getAllRecipe() throws SQLException{
		ArrayList<String> allRecipe = new ArrayList<String>();
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from recipe");
		// Get all name of recipe
		while (rs.next()) {
			String recipeName = rs.getString("name");
			allRecipe.add(recipeName);
		}
		return allRecipe;
	}
	
	
	// Updating ingredient storage object to database
	public static int updateStorageIngredient(StorageIngredient storageIngredient) {
		// Get the storage ingredient object and update it in the database
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

	// Updating storage ingredient with name and new amount input
	public static void updateSingleStorage(String storageName, double newAmount) {
		String sql = "update ingredient_storage set ingredient_amount=? where ingredient_name = ?";
		Connection conn = DBConnection.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, newAmount);
			ps.setString(2,storageName);
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully updating for storage ingredient!\n");
			return ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;

	}

	// Adding new capacity equipment into the database
	public static void addNewEquipment(double newCapacity) {
		String sql = "insert into equipment(capacity,unit)values(?,?)";
		Connection conn = DBConnection.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, newCapacity);
			ps.setString(2,"ml");
			int result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Getting all equipment capacity from the database
	public static ArrayList<Double> getAllEquipment() throws SQLException {
		ArrayList<Double> equipmentList = new ArrayList<Double>();
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select * from equipment");
		// Get all information from ingredient_storage ingredient
		while (rs.next()) {
			double capacity= rs.getDouble("capacity");
			equipmentList.add(capacity);
		}
		return equipmentList;
	}

	// Deleting specific equipment with capacity input
	public static void deletingEquipment(double deletingCapacity) {
		String sql1 = "delete from equipment where capacity=?";
		Connection conn = DBConnection.getConnection();

		try {
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setDouble(1, deletingCapacity);
			int result1 = ps1.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Updating ingredient recipe to database
	public static void updateRecipeIngredient(String recipeName, String ingredientName, double newAmount) {

		String sql = "update ingredient_recipe set ingredient_amount=? where recipe_name=? and ingredient_name = ?";
		Connection conn = DBConnection.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, newAmount);
			ps.setString(2,recipeName);
			ps.setString(3,ingredientName);
			int result = ps.executeUpdate();
			System.out.println("\nSuccessfully updating for recipe ingredient!\n");
			return ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ;
	}


	// Get single storage ingredient with the ingredient name input
	public static StorageIngredient getSingleStorageIngredient(String StorageIngredientName) throws SQLException {
		Connection conn = DBConnection.getConnection();
		String sql = "select * from ingredient_storage where ingredient_name=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, StorageIngredientName);
		ResultSet rs = ps.executeQuery();
		String ingredientName;
		double ingredientAmount;
		String ingredientUnit;
		StorageIngredient si=null;
		while (rs.next()) {
			ingredientName = rs.getString("ingredient_name");
			ingredientAmount = rs.getDouble("ingredient_amount");
			ingredientUnit = rs.getString("ingredient_unit");
			si = new StorageIngredient(ingredientName,ingredientAmount,ingredientUnit);
		}		
		return si;
	} 

	// Getting all storage ingredient in the form of name and object
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
		// Looping in the database and check whether get the satisfied capacity for the equipment
		while (rs.next()) {//下一行
			int capacity = rs.getInt("capacity");//获取id这一列
			if(capacity>=batchSize)
				return true;
		}
		return false;
	}

	// Check the all the recipe with satisfied recipe capacity from the input batch size
	public static ArrayList<String> calculateSatifiedRecipe(Map<String, StorageIngredient> AllStroageIngredient, double batchsize, int satisfy) throws SQLException, IOException, ClassNotFoundException {
		Connection conn = DBConnection.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("select value from recipe");
		ArrayList<String> recipeName = new ArrayList();
		// Get all information from ingredient_storage ingredient
		while (rs.next()) {
			ObjectInputStream ois = new ObjectInputStream(
					rs.getBinaryStream("value"));
			Recipe recipe = (Recipe) ois.readObject();
			if(recipeIngredientSatisfaction(recipe,AllStroageIngredient,batchsize, satisfy)==true)
				recipeName.add(recipe.getRecipeName());
		}
		return recipeName;
	}

	// Check single recipe satisfied for the batch size
	public static boolean recipeIngredientSatisfaction(Recipe recipe, Map<String, StorageIngredient> AllStroageIngredient, double batchSize, int satisfy) {
		Map<String,Double>recipeMap = recipe.convertValue(recipe.getRecipeName(), batchSize, "ml", "g");
		int sign = 0;
		// Comparison for the storage value and the converted value
		for(String key:recipeMap.keySet()) {
			double storageValue = AllStroageIngredient.get(key).getAmount();
			double recipeValue = recipeMap.get(key);
			if(satisfy==1) {
				if(recipeValue>storageValue) {
					return false;
				}
			}
			else {
				if(recipeValue>storageValue) {
					sign = 1;
					return true;
				}
			}
		}
		if(sign==0&&satisfy==1)
			return true;
		else
			return false;
	}

	// Get the missing ingredient capacity from the converted ingredient and storage ingredient calculation
	public static Map<String,Double> recipeMissingCalculation(String recipeName, double batchSize) throws SQLException{
		Map<String,Double> missingIngredient = new LinkedHashMap ();
		Recipe recipe = DataBase.getRecipe(recipeName);
		Map<String,Double>recipeMap = recipe.convertValue(recipeName, batchSize, "ml", "g");
		Map<String,StorageIngredient> allStorageIngredient = getAllStorageIngredient();
		for(String key:recipeMap.keySet()) {
			if(recipeMap.get(key)>allStorageIngredient.get(key).getAmount()) {
				double difference = recipeMap.get(key) - allStorageIngredient.get(key).getAmount();
				missingIngredient.put(key, difference);
			}
		}
		return missingIngredient;
	}
}


package mainPart;
import com.dataBase.*;

import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

public class Brew {
	// The attribute for brew class
	private double batchSize;
	private String batchUnit;
	private String ingredientUnit;
	private String recipeName;
	private Note note;
	private int index;
	private java.sql.Date date;
	private java.sql.Time time;
	
	//!!! Constructor  !!!!   
	public Brew(String recipeName, double batchSize,String batchUnit,String ingredientUnit) throws SQLException{
		this.batchSize = batchSize;
		this.batchUnit = batchUnit;
		this.recipeName = recipeName;
		this.ingredientUnit = ingredientUnit;
	}
	// Empty constructor
	public Brew() {
		// TODO Auto-generated constructor stub
	}
	// !!! Implementation the Brew operation !!!
	public void implement(String recipeName, double batchSize) throws SQLException {
		// Get the recipe object at first
		Recipe r= DataBase.getRecipe(recipeName);
		// Do the batch size converting
		Map<String,Double>ingredientList = r.convertValue(recipeName, batchSize, "ml", "g");
		for(String key:ingredientList.keySet()) {
			StorageIngredient si = DataBase.getSingleStorageIngredient(key);
			double newAmount = si.getAmount() - ingredientList.get(key);
			DataBase.updateSingleStorage(si.getIngredientName(), newAmount);
		}
		// Record the brewing date and time
		date = new java.sql.Date(System.currentTimeMillis());
		time = new java.sql.Time(System.currentTimeMillis());
		// Record the brewing information
		DataBase.brewRecording(batchSize, batchUnit, recipeName, date, time);
	}
	
	// Get the brew object with no note written
	public ArrayList<Brew> getAllBrewExperienceWithoutNote() throws SQLException, ParseException{
		return DataBase.getAllBrewInformationWithoutNote();
	}
	// 
	public Brew getLastBrew() throws SQLException, ParseException {
		return DataBase.getLastBrewing();
	}
	
	//!! Below is the basic getting and setting for the attribute in the Brew class
	public String getRecipeName() {
		return recipeName;
	}

	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}

	public int getIndex() {
		return index;
	}


	public void setIndex(int index) {
		this.index = index;
	}


	public void addNote(Note inputNote) {
		this.note = inputNote;
	}
	
	public String getBatchUnit() {
		return batchUnit;
	}

	public void setBatchUnit(String batchUnit) {
		this.batchUnit = batchUnit;
	}

	public void setDate(Date date2) {
		this.date = (java.sql.Date) date2;
	}

	public void setTime(Time time2) {
		this.time = time2;
	}

	public double getBatchSize() {
		return batchSize;
	}

	public void setBatchSize(double batchSize) {
		this.batchSize = batchSize;
	}

	public java.util.Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

}

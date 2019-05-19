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

import com.exception.LargerThanEquip;

public class Brew {
	private double batchSize;
	private String batchUnit;
	private String ingredientUnit;
	private String recipeName;
	private Note note;
	private int index;
	private java.sql.Date date;
	private java.sql.Time time;
	
	
	
	//!!! Constructor  !!!!   
	public Brew(String recipeName, double batchSize,String batchUnit,String ingredientUnit) throws LargerThanEquip, SQLException{
		this.batchSize = batchSize;
		this.batchUnit = batchUnit;
		this.recipeName = recipeName;
		this.ingredientUnit = ingredientUnit;
	}
	
	
	public Brew() {
		// TODO Auto-generated constructor stub
	}


	// !!! Implementation !!!
	public void implement(String recipeName, double batchSize) throws SQLException {
		Recipe r= DataBase.getRecipe(recipeName);
		Map<String,Double>ingredientList = r.convertValue(recipeName, batchSize, "ml", "g");
		for(String key:ingredientList.keySet()) {
			StorageIngredient si = DataBase.getSingleStorageIngredient(key);
			double newAmount = si.getAmount() - ingredientList.get(key);
			DataBase.updateSingleStorage(si.getIngredientName(), newAmount);
		}
		date = new java.sql.Date(System.currentTimeMillis());
		time = new java.sql.Time(System.currentTimeMillis());
		DataBase.brewRecording(batchSize, batchUnit, recipeName, date, time);
	}
	
	
	public ArrayList<Brew> getAllBrewExperienceWithoutNote() throws SQLException, LargerThanEquip, ParseException{
		return DataBase.getAllBrewInformationWithoutNote();
	}
	
	public Brew getLastBrew() throws LargerThanEquip, SQLException, ParseException {
		return DataBase.getLastBrewing();
	}
	
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

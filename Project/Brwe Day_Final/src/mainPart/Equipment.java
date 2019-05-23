package mainPart;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.dataBase.DataBase;
import com.ui.InputUI;

public class Equipment {
	// Basic attribute for the equipment class
	private double capacity;
	private String capUnit;
	
	public Equipment(double cap, String unit) {
		this.capacity = cap;
		this.capUnit = unit;
	}
	
	public Equipment() {
		// TODO Auto-generated constructor stub
	}

	// Adding new equipment
	public void addEquipment(double newCapacity) {
		DataBase.addNewEquipment(newCapacity);
	}	
	// Subtract the equipment from the database
	public void subtractEquipment(double deleteCapacity) {
		DataBase.deletingEquipment(deleteCapacity);
	}
	
	// Get all equipment capacity
	public ArrayList<Double> getAllEquipment() throws SQLException{
		return DataBase.getAllEquipment();
	} 
	
	// Check equipment for batch size
	public boolean checkBatchSize(double batchSize) throws SQLException {
		return DataBase.checkBatchSize(batchSize);
	}
	
	// Check for the valid number input
	public int checkValidNumber(String inputText) {
		double batchSize;
		// Using regular expression to match the correct number input
		if(inputText.matches("-?\\d+(\\.\\d+)?")==true) {
			batchSize=Double.parseDouble(inputText);
			if(batchSize<=0) {
				return 1;
			}
			return 2;
		}
		else 
			return 3;
	}
	
	// !! Below is the basic function for getting and setting attribute
	public double getCapacity() {
		return capacity;
	}
	
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public String getCapUnit() {
		return capUnit;
	}

	public void setCapUnit(String capUnit) {
		this.capUnit = capUnit;
	}
	
}

package com.controller;
import java.sql.SQLException;

import com.dataBase.*;
import mainPart.StorageIngredient;
public class ControllerAddingIngredient {
	StorageIngredient storIngredient;
	public ControllerAddingIngredient() {
		
	}
	
	public String conAddingIngredient(String maltT,String hopT,String yeastT,String sugarT,String additiveT) throws SQLException {
		storIngredient = new StorageIngredient();
		int check1 = storIngredient.checkStringInput(maltT);
		int check2 = storIngredient.checkStringInput(hopT);
		int check3 = storIngredient.checkStringInput(yeastT);
		int check4 = storIngredient.checkStringInput(sugarT);
		int check5 = storIngredient.checkStringInput(additiveT);
		
		if(check1==3 || check2==3 || check3==3 || check4==3 || check5==3 ) {
			return "invalid";
		}
		else if(check1==1 || check2==1 || check3==1 || check4==1 || check5==1 ) {
			return "less than 0";
		}
		else {
			String get = storIngredient.checkForUpdating(maltT,hopT,yeastT,sugarT,additiveT,1);
				return get;
		}
	}
}

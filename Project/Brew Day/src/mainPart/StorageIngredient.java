package mainPart;
import java.sql.SQLException;
import java.util.Map;

import com.dataBase.DataBase;

public class StorageIngredient extends Ingredient {

	// Constructor1
	public StorageIngredient(String ingredientName, double amount, String unit) {
		super(ingredientName, amount, unit);
	}
	// Constructor2
	public StorageIngredient() {
		super();
	}

	public Map<String, StorageIngredient> getAllStorageIngredient() throws SQLException {
		return DataBase.getAllStorageIngredient();
	}

	// Updating single storage ingredient
	public void updateSingleStorage(String ingredientName, double newAmount) {
		DataBase.updateSingleStorage(ingredientName,newAmount);
	}

	// Checking for single adding
	// 1 for adding model, 0 for minus model
	public int checkStringInput(String inputSize) {

		if(inputSize.length()==0)
			return 0;
		else {
			Equipment equip = new Equipment();
			int check = equip.checkValidNumber(inputSize);
			if(check==1)
				return 1;
			if(check==2)
				return 2;
			if(check==3)
				return 3;
		}
		return 2;
	}

	// Checking for updating status
	public String checkForUpdating(String maltT,String hopT,String yeastT,String sugarT,String additiveT,int model) throws SQLException {
		Map<String,StorageIngredient> storageList =  this.getAllStorageIngredient();
		double malt=storageList.get("malts").getAmount(),
				hop=storageList.get("hops").getAmount(),
				yeast=storageList.get("yeasts").getAmount(),
				sugar=storageList.get("sugars").getAmount(),
				additive=storageList.get("additives").getAmount();
		if(maltT.length()!=0) {
			try{
				malt=Double.parseDouble(maltT);
				if(model==1)
					malt=malt+storageList.get("malts").getAmount();
				else
					malt=storageList.get("malts").getAmount()-malt;
				if(malt<0)
					return "malts";
			}catch(Throwable e1){

			}
		}
		if(hopT.length()!=0) {
			try{
				hop=Double.parseDouble(hopT);
				if(model==1)
					hop=hop+storageList.get("hops").getAmount();
				else
					hop=storageList.get("hops").getAmount()-hop;
				if(hop<0)
					return "hops";
			}catch(Throwable e1){

			}
		}
		if(yeastT.length()!=0) {
			try{
				yeast=Double.parseDouble(yeastT);
				if(model==1)
					yeast=yeast+storageList.get("yeasts").getAmount();
				else
					yeast=storageList.get("yeasts").getAmount()-yeast;
				if(yeast<0)
					return "yeasts";
			}catch(Throwable e1){

			}
		}
		if(sugarT.length()!=0) {
			try{
				sugar=Double.parseDouble(sugarT);
				if(model==1)
					sugar=sugar+storageList.get("sugars").getAmount();
				else
					sugar=storageList.get("sugars").getAmount()-sugar;
				if(sugar<0)
					return "sugars";
			}catch(Throwable e1){

			}
		}
		if(additiveT.length()!=0) {
			try{
				additive=Double.parseDouble(additiveT);
				if(model==1)
					additive=additive+storageList.get("additives").getAmount();
				else
					additive=storageList.get("additives").getAmount()-additive;
				if(additive<0)
					return "additives";
			}catch(Throwable e1){

			}
		}

		this.updateSingleStorage("malts",malt);
		this.updateSingleStorage("hops",hop);
		this.updateSingleStorage("yeasts",yeast);
		this.updateSingleStorage("sugars",sugar);
		this.updateSingleStorage("additives",additive);
		return "good";
	}


	public void addAmount(double addNum) {
		this.updateAmount(addNum);
	}

	public void subtractAmount(double subNum) {
		this.updateAmount(-subNum);
	}
}

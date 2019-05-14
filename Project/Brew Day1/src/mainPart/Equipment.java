package mainPart;

public class Equipment {
	private double capacity;
	private String capUnit;
	
	public Equipment(double cap, String unit) {
		this.capacity = cap;
		this.capUnit = unit;
	}

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

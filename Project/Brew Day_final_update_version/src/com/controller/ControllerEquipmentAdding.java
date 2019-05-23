package com.controller;
import com.dataBase.*;
import mainPart.Equipment;
public class ControllerEquipmentAdding {
		private Equipment equip;
		public ControllerEquipmentAdding() {
			
		}
		// This is the method for controller to adding equipment
		public void conEquipmentAdding(double newCapacity) {
			//set a new equipment
			equip = new Equipment(newCapacity, "ml");
			equip.addEquipment(equip.getCapacity());
		}
}

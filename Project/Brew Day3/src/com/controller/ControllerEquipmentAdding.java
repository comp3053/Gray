package com.controller;
import com.dataBase.*;
import mainPart.Equipment;
public class ControllerEquipmentAdding {
		private Equipment equip;
		public ControllerEquipmentAdding() {
			
		}
		
		public void conEquipmentAdding(double newCapacity) {
			equip = new Equipment(newCapacity, "ml");
			equip.addEquipment(equip.getCapacity());
		}
}

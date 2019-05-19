package com.controller;
import com.dataBase.*;
import mainPart.Equipment;
public class ControllerEquipmentDeleting {
	private Equipment equip;
	public ControllerEquipmentDeleting() {
		
	}
	public void conEquipmentDeleting(double deleteCapacity) {
		equip = new Equipment();
		equip.subtractEquipment(deleteCapacity);
	}
}

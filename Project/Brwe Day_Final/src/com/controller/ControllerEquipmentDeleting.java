package com.controller;
import com.dataBase.*;
import mainPart.Equipment;
public class ControllerEquipmentDeleting {
	private Equipment equip;
	public ControllerEquipmentDeleting() {
		// This is the method for controller to deleting equipment
	}
	public void conEquipmentDeleting(double deleteCapacity) {
		//set new equipment
		equip = new Equipment();
		equip.subtractEquipment(deleteCapacity);
	}
}

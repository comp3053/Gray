package com.exception;

public class LargerThanEquip extends Exception {

	public LargerThanEquip() {
		System.out.println("The batch size is larger than the equipment capacity");
	}
}

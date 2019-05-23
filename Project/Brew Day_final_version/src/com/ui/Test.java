package com.ui;
import com.controller.*;

// This class is for open the main page
public class Test {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				// Set up the main page for running the program
				MainPageView vm = new MainPageView();
			}
		});
	}
}
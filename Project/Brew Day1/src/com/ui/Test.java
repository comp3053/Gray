package com.ui;
import com.controller.*;
import com.listener.*;

public class Test {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainPageView vm = new MainPageView();
			}
		});
	}
}
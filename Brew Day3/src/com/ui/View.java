package com.ui;
import com.listener.*;
import javax.swing.JFrame;

public abstract class View extends JFrame implements ModelListener{

	public View() {		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// End when close
	}

}

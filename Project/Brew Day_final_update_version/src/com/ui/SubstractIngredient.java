package com.ui;
// import packages
import com.controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.controller.ControllerAddingIngredient;
import com.dataBase.DataBase;

import mainPart.StorageIngredient;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class SubstractIngredient extends View { // this class extends View
	private ControllerSubtractingIngredient controllerSI; 
	private StorageIngredient storageIng;
	public SubstractIngredient() throws SQLException {
		Font f=new Font("ÂÞÂí",Font.BOLD,19); // font
		storageIng = new StorageIngredient(); // a StorageIngredient
		int gap = 100; // define gap is 100
		this.setTitle("Subtract amount of ingredient");//set title
		this.setSize(800, 600); // set size
		this.setLocation(500, 200); // set location
		this.setLayout(null);

		JPanel pInput = new JPanel(); // create a JPanel
		pInput.setBounds(gap, gap, 500, 200); // set bounds
		pInput.setLayout(new GridLayout(7,1,10,10)); // set layout

		// Get the current value for the storage ingredient
		Map<String,StorageIngredient> storageList =  storageIng.getAllStorageIngredient();

		// Recipe Name
		JLabel hint = new JLabel("Ingredient");     // JLabel 1
		JLabel name = new JLabel("Subtract Amount");// JLabel 2
		JLabel value = new JLabel("Current Value"); // JLabel 3
		hint.setFont(f);name.setFont(f);value.setFont(f);

		//sustract amount of Malts
		JLabel malts = new JLabel("Malts:");
		JTextField maltsText = new JTextField();
		BigDecimal bg1 = new BigDecimal(storageList.get("malts").getAmount()).setScale(2, RoundingMode.UP);
		JLabel maltsNewValue = new JLabel(bg1+" g");
		malts.setFont(f);maltsNewValue.setFont(f);

		//sustract amount of Hops
		JLabel hops = new JLabel("Hops:");
		JTextField hopsText = new JTextField();
		BigDecimal bg2 = new BigDecimal(storageList.get("hops").getAmount()).setScale(2, RoundingMode.UP);
		JLabel hopsNewValue = new JLabel(bg2+" g");
		hops.setFont(f);hopsNewValue.setFont(f);
		
		//sustract amount of Yeasts
		JLabel yeasts = new JLabel("Yeasts:");
		JTextField yeastsText = new JTextField();
		BigDecimal bg3 = new BigDecimal(storageList.get("yeasts").getAmount()).setScale(2, RoundingMode.UP);
		JLabel yeastsNewValue = new JLabel(bg3+" g");
		yeasts.setFont(f);yeastsNewValue.setFont(f);
		
		//sustract amount of Sugars
		JLabel sugars = new JLabel("Sugars:");
		JTextField sugarsText = new JTextField();
		BigDecimal bg4 = new BigDecimal(storageList.get("sugars").getAmount()).setScale(2, RoundingMode.UP);
		JLabel sugarsNewValue = new JLabel(bg4+" g");
		sugars.setFont(f);sugarsNewValue.setFont(f);

		//sustract amount of Additives
		JLabel additives = new JLabel("Additives:");
		JTextField additivesText = new JTextField();
		BigDecimal bg5 = new BigDecimal(storageList.get("additives").getAmount()).setScale(2, RoundingMode.UP);
		JLabel additivesNewValue = new JLabel(bg5+" g");
		additives.setFont(f);additivesNewValue.setFont(f);

		JButton b1 = new JButton("Subtract"); // create a button 1
		JButton b2 = new JButton("Back"); // create a button 2
		b1.setFont(f);b2.setFont(f); // set the font

		b1.addActionListener(new ActionListener() { // add action listener	
			@Override
			public void actionPerformed(ActionEvent e) { // add action event
				String check = null;
				controllerSI = new ControllerSubtractingIngredient(); // controller
				try {
					check = controllerSI.conSubtractingIngredient(maltsText.getText(),hopsText.getText(),yeastsText.getText(),sugarsText.getText(),additivesText.getText());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(check.equals("invalid")) // error input 1£º invalid
					JOptionPane.showMessageDialog(null, "Input should be number!", "Warning",JOptionPane.WARNING_MESSAGE); 
				else if(check.equals("less than 0")) // error input 1£º <0
					JOptionPane.showMessageDialog(null, "The input number should be larger than 0", "Warning",JOptionPane.WARNING_MESSAGE); 
				else if(check.equals("good")) { // correct input!!!
					JOptionPane.showMessageDialog(null, "Successfully Subtracting Ingredient", "Recipe Subtracting",JOptionPane.INFORMATION_MESSAGE); 
					closeThis(); // close
					try {
						new SubstractIngredient(); // substract ingredint 
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Ingredient"+" \""+check+" \""+" in the storage is becoming less than zero", "Warning",JOptionPane.WARNING_MESSAGE); 
				}
			}
		});

		b2.addActionListener(new ActionListener() {	 // add action listener
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainIngredientUI();
			}
		});

		pInput.add(hint); // add to pInput
		pInput.add(name); // add to pInput
		pInput.add(value); // add to pInput

		pInput.add(malts); // add to pInput
		pInput.add(maltsText); // add to pInput
		pInput.add(maltsNewValue); // add to pInput

		pInput.add(hops);// add to pInput
		pInput.add(hopsText);// add to pInput
		pInput.add(hopsNewValue);// add to pInput

		pInput.add(yeasts);// add to pInput
		pInput.add(yeastsText);// add to pInput
		pInput.add(yeastsNewValue);// add to pInput


		pInput.add(sugars);// add to pInput
		pInput.add(sugarsText);// add to pInput
		pInput.add(sugarsNewValue);// add to pInput


		pInput.add(additives);// add to pInput
		pInput.add(additivesText);// add to pInput
		pInput.add(additivesNewValue);// add to pInput

		b1.setBounds(150, 380, 120, 50); // set bounds of button1
		b2.setBounds(350, 380, 120, 50); // set bounds of button2


		this.add(pInput); // add pInput
		this.add(b1); // add button1
		this.add(b2); // add button2
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setVisible(true); // set to visible

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false); // close
	}
}

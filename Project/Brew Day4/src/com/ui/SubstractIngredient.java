package com.ui;
import com.controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

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
import java.awt.GridLayout;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



public class SubstractIngredient extends JFrame {
	private ControllerSubtractingIngredient controllerSI; 
	private StorageIngredient storageIng;
	public SubstractIngredient() throws SQLException {
		storageIng = new StorageIngredient();
		int gap = 100;
		this.setTitle("Subtract amount of ingredient");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 500, 200);
		pInput.setLayout(new GridLayout(7,1,10,10));

		// Get the current value for the storage ingredient
		Map<String,StorageIngredient> storageList =  storageIng.getAllStorageIngredient();

		// Recipe Name
		JLabel hint = new JLabel("Ingredient");
		JLabel name = new JLabel("Subtract amount for ingredient:");
		JLabel value = new JLabel("Current value");

		//!!!
		JLabel malts = new JLabel("Malts:");
		JTextField maltsText = new JTextField();
		BigDecimal bg1 = new BigDecimal(storageList.get("malts").getAmount()).setScale(2, RoundingMode.UP);
		JLabel maltsNewValue = new JLabel(bg1+" g");

		//!!!
		JLabel hops = new JLabel("Hops:");
		JTextField hopsText = new JTextField();
		BigDecimal bg2 = new BigDecimal(storageList.get("hops").getAmount()).setScale(2, RoundingMode.UP);
		JLabel hopsNewValue = new JLabel(bg2+" g");

		//!!!
		JLabel yeasts = new JLabel("Yeasts:");
		JTextField yeastsText = new JTextField();
		BigDecimal bg3 = new BigDecimal(storageList.get("yeasts").getAmount()).setScale(2, RoundingMode.UP);
		JLabel yeastsNewValue = new JLabel(bg3+" g");

		//!!!
		JLabel sugars = new JLabel("Sugars:");
		JTextField sugarsText = new JTextField();
		BigDecimal bg4 = new BigDecimal(storageList.get("sugars").getAmount()).setScale(2, RoundingMode.UP);
		JLabel sugarsNewValue = new JLabel(bg4+" g");

		//!!!
		JLabel additives = new JLabel("Additives:");
		JTextField additivesText = new JTextField();
		BigDecimal bg5 = new BigDecimal(storageList.get("additives").getAmount()).setScale(2, RoundingMode.UP);
		JLabel additivesNewValue = new JLabel(bg5+" g");


		JButton b1 = new JButton("Subtracting ingredient");
		JButton b2 = new JButton("Back");


		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String check = null;
				controllerSI = new ControllerSubtractingIngredient();
				try {
					check = controllerSI.conSubtractingIngredient(maltsText.getText(),hopsText.getText(),yeastsText.getText(),sugarsText.getText(),additivesText.getText());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(check.equals("invalid")) 
					JOptionPane.showMessageDialog(null, "Input should be number!", "Warning",JOptionPane.WARNING_MESSAGE); 
				else if(check.equals("less than 0"))
					JOptionPane.showMessageDialog(null, "The input number should be larger than 0", "Warning",JOptionPane.WARNING_MESSAGE); 
				else if(check.equals("good")) {
					JOptionPane.showMessageDialog(null, "Successfully Subtracting Ingredient", "Recipe Subtracting",JOptionPane.INFORMATION_MESSAGE); 
					closeThis();
					try {
						new SubstractIngredient();
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

		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainIngredientUI();
			}
		});

		pInput.add(hint);
		pInput.add(name);
		pInput.add(value);

		pInput.add(malts);
		pInput.add(maltsText);
		pInput.add(maltsNewValue);

		pInput.add(hops);
		pInput.add(hopsText);
		pInput.add(hopsNewValue);

		pInput.add(yeasts);
		pInput.add(yeastsText);
		pInput.add(yeastsNewValue);


		pInput.add(sugars);
		pInput.add(sugarsText);
		pInput.add(sugarsNewValue);


		pInput.add(additives);
		pInput.add(additivesText);
		pInput.add(additivesNewValue);

		b1.setBounds(150, 380, 120, 50);
		b2.setBounds(350, 380, 120, 50);


		this.add(pInput);
		this.add(b1);
		this.add(b2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setVisible(true);

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

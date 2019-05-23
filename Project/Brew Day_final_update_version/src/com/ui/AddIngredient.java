package com.ui;
import mainPart.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.controller.ControllerUpdatingRecipe;
import com.dataBase.DataBase;

import mainPart.Recipe;
import mainPart.RecipeIngredient;

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
import com.controller.*;

public class AddIngredient extends View {
	private ControllerAddingIngredient controllerAI;
	private StorageIngredient storageIng;
	public  AddIngredient() throws SQLException{
		// UI setting
		Font f=new Font("ÂÞÂí",Font.BOLD,19);
		storageIng = new StorageIngredient();
		int gap = 100;
        //set the title
		this.setTitle("Adding amount");
		this.setSize(800, 600);
		this.setLocation(500, 200);
		this.setLayout(null);
		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 500, 200);
		pInput.setLayout(new GridLayout(7,1,10,10));
		
		// Get the current value for the storage ingredient
		Map<String,StorageIngredient> storageList =  storageIng.getAllStorageIngredient();
		
		// Recipe Name
		JLabel hint = new JLabel("Ingredient");
		JLabel name = new JLabel("Add Amount");
		JLabel value = new JLabel("Current Value");
		hint.setFont(f);name.setFont(f);value.setFont(f);
		
		// we can input adding amount of Malts here
		JLabel malts = new JLabel("Malts:");
		JTextField maltsText = new JTextField();
		BigDecimal bg1 = new BigDecimal(storageList.get("malts").getAmount()).setScale(2, RoundingMode.UP);
		JLabel maltsNewValue = new JLabel(bg1+" g");
		malts.setFont(f);maltsNewValue.setFont(f);
		
		// we can input adding amount of Hops here
		JLabel hops = new JLabel("Hops:");
		JTextField hopsText = new JTextField();
		BigDecimal bg2 = new BigDecimal(storageList.get("hops").getAmount()).setScale(2, RoundingMode.UP);
		JLabel hopsNewValue = new JLabel(bg2+" g");
		hops.setFont(f);hopsNewValue.setFont(f);

		// we can input adding amount of Yeasts here
		JLabel yeasts = new JLabel("Yeasts:");
		JTextField yeastsText = new JTextField();
		BigDecimal bg3 = new BigDecimal(storageList.get("yeasts").getAmount()).setScale(2, RoundingMode.UP);
		JLabel yeastsNewValue = new JLabel(bg3+" g");
		yeasts.setFont(f);yeastsNewValue.setFont(f);
		
		// we can input adding amount of Sugars here
		JLabel sugars = new JLabel("Sugars:");
		JTextField sugarsText = new JTextField();
		BigDecimal bg4 = new BigDecimal(storageList.get("sugars").getAmount()).setScale(2, RoundingMode.UP);
		JLabel sugarsNewValue = new JLabel(bg4+" g");
		sugars.setFont(f);sugarsNewValue.setFont(f);
		
		// we can input adding amount of Additives here
		JLabel additives = new JLabel("Additives:");
		JTextField additivesText = new JTextField();
		BigDecimal bg5 = new BigDecimal(storageList.get("additives").getAmount()).setScale(2, RoundingMode.UP);
		JLabel additivesNewValue = new JLabel(bg5+" g");
		additives.setFont(f);additivesNewValue.setFont(f);
        
        // add the buttons.
		JButton b1 = new JButton("Add");
		JButton b2 = new JButton("Back");
		b1.setFont(f);b2.setFont(f);
		
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String check = null;
				controllerAI = new ControllerAddingIngredient();
				try {
					check = controllerAI.conAddingIngredient(maltsText.getText(),hopsText.getText(),yeastsText.getText(),sugarsText.getText(),additivesText.getText());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}//if we do nothing and click buttons
				if(maltsText.getText().length()==0 && hopsText.getText().length()==0 && yeastsText.getText().length()==0
						&& sugarsText.getText().length()==0 && additivesText.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "You have adding nothing!", "Warning",JOptionPane.WARNING_MESSAGE);
				}//if input is not number for amount of ingredients.
				else if(check.equals("invalid")) 
					JOptionPane.showMessageDialog(null, "Input should be number!", "Warning",JOptionPane.WARNING_MESSAGE);
                //if input number does not be larger than 0
				else if(check.equals("less than 0"))
					JOptionPane.showMessageDialog(null, "The input number should be larger than 0", "Warning",JOptionPane.WARNING_MESSAGE);
                //have a successfully adding
				else if(check.equals("good")) {
					JOptionPane.showMessageDialog(null, "Successfully Adding Ingredient", "Recipe Adding",JOptionPane.INFORMATION_MESSAGE); 
					closeThis();
					try {
						new AddIngredient();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Ingredient"+" \""+check+" \""+" in the storage in becoming less than zero", "Warning",JOptionPane.WARNING_MESSAGE); 
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
		// add all contents into the interface
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

package com.ui;
// import packages
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.dataBase.DataBase;

import mainPart.Brew;
import mainPart.Note;
import mainPart.Recipe;
import mainPart.StorageIngredient;

public class RecipeMissingSomeUI extends View { // this class extends View
	
	private StorageIngredient storIng;
	private Recipe recipe;
	public RecipeMissingSomeUI(double batchSize) throws SQLException, ClassNotFoundException, IOException{
		Font f1=new Font("ÂÞÂí",Font.BOLD,20); // font
		int gap = 10;
		JFrame f = new JFrame("Recipe Missing Some Ingredient");
		this.setTitle("Recipe Missing"); //set title
		this.setSize(800, 400); // set size
		this.setLocation(500, 200); // set location
		this.setLayout(new FlowLayout());

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 400, 120); // set bounds
		pInput.setLayout(new GridLayout(5,1,gap,gap));

		JLabel recipeSelection = new JLabel("Missing Recipe");
		JTextField recipeSelectionText = new JTextField();
		recipeSelection.setFont(f1);
		// Change input button
		JButton back = new JButton("Change input");
		//back.setFont(f1);
		// Create the draw box
		JComboBox<String> jcb = new JComboBox<String>();
		
		// Text for the missing ingredient
		JLabel j1 = new JLabel();
		Font f2=new Font("ÂÞÂí",Font.CENTER_BASELINE,15);
		j1.setFont(f2);
		// Check unsatisfied recipe
		storIng = new StorageIngredient();
		Map<String, StorageIngredient> allStorageIngredient = storIng.getAllStorageIngredient(); 

		recipe = new Recipe();
		// 0 means do the unsatisfied part of calculation
		// Compare with the batch size with current storage ingredient to get the unsatisfied recipe
		ArrayList<String> recipeList = recipe.calculateSatifiedRecipe(allStorageIngredient, batchSize,0);
		
		// Store the missing recipe
		Map<String,Map<String,BigDecimal>> missingRecipe = new HashMap();

		// Loop to calculate the missing ingredient from the recipe
		for(int i=0;i<recipeList.size();i++) {
			Map<String,Double>missingIngredient = recipe.recipeMissingCalculation(recipeList.get(i),batchSize); 
			jcb.addItem(recipeList.get(i)); // addItem
			Map<String,BigDecimal> temp =  new HashMap(); //mapping
			for(String key:missingIngredient.keySet()) {
				BigDecimal bg = new BigDecimal(missingIngredient.get(key)).setScale(2, RoundingMode.UP);
				temp.put(key, bg);
				missingRecipe.put(recipeList.get(i), temp);// the missing recipe
			}
		}
		
		
		Map<String,BigDecimal> init = missingRecipe.get(recipeList.get(0)); //mapping
		String one= "";
		for(String key:init.keySet()) { 
			one+=key+" "+init.get(key)+"g. ";
		}
		j1.setText(one); // set the text
		
		
		
		jcb.addActionListener(new ActionListener() { // add action listener
			public void actionPerformed(ActionEvent e) { // add action event
				JComboBox combo = (JComboBox) e.getSource(); // JComboBox
				if (missingRecipe.containsKey(combo.getSelectedItem())) { // missing recipe
					Map<String,BigDecimal>  temp = missingRecipe.get(combo.getSelectedItem());
					String out= "";
					for(String key:temp.keySet()) {
						out+=key+" "+temp.get(key)+"g. ";
					}
					j1.setText(out);
				}	 
			}
		});

		
		JLabel noteContent = new JLabel("Missing ingredient(s):");
		noteContent.setFont(f1);
		pInput.add(recipeSelection); // add to pInput
		pInput.add(jcb); // add to pInput
		pInput.add(noteContent); // add to pInput
		pInput.add(j1); // add to pInput
		back.setFont(f2); // set font
		back.setBounds(80, 30, 150, 40); // set bounds
	


		this.add(pInput);// add pInput
		pInput.add(back);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		back.addActionListener(new ActionListener() {// add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event
				closeThis(); // close
				new InputUI();
			}
		});


		this.setVisible(true); // set to visible

	}

	protected void closeThis() {
		this.setVisible(false); // close
	}


}
package com.ui;

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
import com.exception.LargerThanEquip;

import mainPart.Brew;
import mainPart.Note;
import mainPart.Recipe;
import mainPart.StorageIngredient;

public class RecipeMissingSomeUI extends JFrame {
	
	private StorageIngredient storIng;
	private Recipe recipe;
	public RecipeMissingSomeUI(double batchSize) throws SQLException, ClassNotFoundException, IOException{
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Font f1=new Font("ÂÞÂí",Font.BOLD,20);
		int gap = 10;
		JFrame f = new JFrame("Recipe Missing Some Ingredient");
		this.setTitle("Recipe Missing");
		this.setSize(800, 400);
		this.setLocation(500, 200);
		this.setLayout(new FlowLayout());

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 400, 120);
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
			jcb.addItem(recipeList.get(i));
			Map<String,BigDecimal> temp =  new HashMap();
			for(String key:missingIngredient.keySet()) {
				BigDecimal bg = new BigDecimal(missingIngredient.get(key)).setScale(2, RoundingMode.UP);
				temp.put(key, bg);
				missingRecipe.put(recipeList.get(i), temp);
			}
		}
		
		
		Map<String,BigDecimal> init = missingRecipe.get(recipeList.get(0));
		String one= "";
		for(String key:init.keySet()) {
			one+=key+" "+init.get(key)+"g. ";
		}
		j1.setText(one);
		
		
		
		jcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();
				if (missingRecipe.containsKey(combo.getSelectedItem())) {
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
		pInput.add(recipeSelection);
		pInput.add(jcb);
		pInput.add(noteContent);
		pInput.add(j1);
		back.setFont(f2);
		back.setBounds(80, 30, 150, 40);
	


		this.add(pInput);
		pInput.add(back);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		back.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new InputUI();
			}
		});


		this.setVisible(true);

	}

	protected void closeThis() {
		this.setVisible(false);
	}


}
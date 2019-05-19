package com.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dataBase.DataBase;

import mainPart.Recipe;
import mainPart.StorageIngredient;

public class RecipeMissingSomeUI extends JFrame {
	private StorageIngredient storIng;
	private Recipe recipe;
	public RecipeMissingSomeUI(double batchSize) throws SQLException, ClassNotFoundException, IOException {
		this.setTitle("RecipeMissingSomeUI");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		//set the location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(4, 1, 20, 10));
		
		// Check unsatisfied recipe
		storIng = new StorageIngredient();
		 Map<String, StorageIngredient> allStorageIngredient = storIng.getAllStorageIngredient(); 
		 // 0 means do the unsatisfied part of calculation
		 recipe = new Recipe();
		 ArrayList<String> recipeList = recipe.calculateSatifiedRecipe(allStorageIngredient, batchSize,0);   
		 for(int i=0;i<recipeList.size();i++) {
			 Map<String,Double>missingIngredient = recipe.recipeMissingCalculation(recipeList.get(i),batchSize);  
			 p.add(new JLabel(recipeList.get(i)+" missing:"));
			 for(String key:missingIngredient.keySet()) {
				 BigDecimal bg = new BigDecimal(missingIngredient.get(key)).setScale(2, RoundingMode.UP);
				 p.add(new JLabel(key+" "+bg+" g."));
			 }
		 }
		
		
		this.add(new JLabel("Recipe missing some inngredient"));
		this.add(p); // Add panel to the frame.
		JButton b1 =new JButton("Change the input");
		this.add(b1);
		
		b1.addActionListener(new ActionListener() {	
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
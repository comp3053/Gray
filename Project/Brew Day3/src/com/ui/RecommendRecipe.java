package com.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dataBase.DataBase;

import mainPart.Recipe;
import mainPart.StorageIngredient;

public class RecommendRecipe extends JFrame {
	private StorageIngredient storIng;
	private Recipe recipe;
	public RecommendRecipe(double batchSize) throws SQLException, ClassNotFoundException, IOException {
		this.setTitle("MainPageUI");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		//set the location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		this.add(new JLabel("Recommedned recipes"));
		this.add(p); // Add panel to the frame.
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(4, 1, 20, 10));
		storIng = new StorageIngredient();
		// Check the satisfied recipe
		 Map<String, StorageIngredient> allStorageIngredient = storIng.getAllStorageIngredient(); 
		 // The last 1 means do the satisfaction part 
		 recipe = new Recipe();
		 ArrayList<String> recipeList = recipe.calculateSatifiedRecipe(allStorageIngredient, batchSize,1);
		 ArrayList<JCheckBox> jbStore = new ArrayList<JCheckBox>();
		 for(int i=0;i<recipeList.size();i++) {
			 JCheckBox j = new JCheckBox(recipeList.get(i));
			 jbStore.add(j);
			 p.add(j);
		 }
	
		
		 this.add(new JLabel("       "));
		JButton b1 = new JButton("Change the input");
		JButton b2 = new JButton("Select");
		this.add(b1);
		this.add(b2);

		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new InputUI();
			}
		});
		
		
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				 for(int i=0;i<jbStore.size();i++) {
					 if(jbStore.get(i).isSelected()) {
						 System.out.println(jbStore.get(i).getText());
						 closeThis();
						 new RecipeName(jbStore.get(i).getText(),batchSize);
					 }
					 else
						 continue;
				 }
				
			}
		});
		
		
		
		
		this.setVisible(true);
	
	}
	
	protected void closeThis() {
		this.setVisible(false);
	}
	
	
}

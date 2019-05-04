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

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.dataBase.DataBase;

import mainPart.StorageIngredient;

public class RecommendRecipe extends JFrame {
	
	public RecommendRecipe(int batchSize) throws SQLException, ClassNotFoundException, IOException {
		this.setTitle("MainPageUI");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		this.add(new JLabel("Recommedned recipes"));
		this.add(p); // Add panel to the frame.
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(4, 1, 20, 10));
		
		// Check the satisfied recipe
		 Map<String, StorageIngredient> allStorageIngredient = DataBase.getAllStorageIngredient();
		 ArrayList<String> recipeList = DataBase.calculateSatifiedRecipe(allStorageIngredient, batchSize);
		 for(int i=0;i<recipeList.size();i++) {
			 p.add(new JCheckBox(recipeList.get(i)));
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
				closeThis();
				new RecipeName();
			}
		});
		
		
		
		
		this.setVisible(true);
	
	}
	
	protected void closeThis() {
		this.setVisible(false);
	}
	
	
}

package com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.dataBase.DataBase;

import mainPart.Recipe;
import mainPart.StorageIngredient;

public class RecommendRecipe extends JFrame {
	private StorageIngredient storIng;
	private Recipe recipe;
	public RecommendRecipe(double batchSize) throws SQLException, ClassNotFoundException, IOException {
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
	
		
		 
		     
	        JScrollPane   sp   =   new  JScrollPane(p);
	        sp.setPreferredSize(new Dimension(100,100));
	        this.getContentPane().add(sp,BorderLayout.LINE_END); 
	        
	        
		this.add(new JLabel("       "));
		JButton b1 = new JButton("Back");
		JButton b2 = new JButton("Select");
		JButton b3 = new JButton("Recipe with missing");
		this.add(b1);
		this.add(b2);
		this.add(b3);
		

		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new InputUI();
			}
		});
		
		b3.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int status = 0;
				String first = null;
				 for(int i=0;i<jbStore.size();i++) {
					 if(jbStore.get(i).isSelected()) {
						 if(first == null)
							 first = jbStore.get(i).getText();
						 status+=1;
					 }
				 }
				 if(status==0) {
					 JOptionPane.showMessageDialog(null, "You are supposed to select one recipe!", "Warning",JOptionPane.WARNING_MESSAGE); 
				 }
				 else if(status==1) {
					 closeThis();
					 try {
						new RecipeMissingSomeUI(batchSize);
					} catch (ClassNotFoundException | SQLException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "More than one recipe is not allowed to select!", "Warning",JOptionPane.WARNING_MESSAGE); 
				 }
			}
		});
		
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				int status = 0;
				String first = null;
				 for(int i=0;i<jbStore.size();i++) {
					 if(jbStore.get(i).isSelected()) {
						 if(first == null)
							 first = jbStore.get(i).getText();
						 status+=1;
					 }
				 }
				 if(status==0) {
					 JOptionPane.showMessageDialog(null, "You are supposed to select one recipe!", "Warning",JOptionPane.WARNING_MESSAGE); 
				 }
				 else if(status==1) {
					 closeThis();
					 new RecipeName(first,batchSize);
				 }
				 else {
					 JOptionPane.showMessageDialog(null, "More than one recipe is not allowed to select!", "Warning",JOptionPane.WARNING_MESSAGE); 
				 }
			}
		});
		
		
		
		
		this.setVisible(true);
	
	}
	
	protected void closeThis() {
		this.setVisible(false);
	}
	
	
}

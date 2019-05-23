package com.ui;
// import packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.dataBase.DataBase;

import mainPart.Recipe;
import mainPart.StorageIngredient;

public class RecommendRecipe extends View { // this class extends View
	private StorageIngredient storIng;
	private Recipe recipe;
	public RecommendRecipe(double batchSize) throws SQLException, ClassNotFoundException, IOException {
		this.setTitle("MainPageUI"); //set title
		Font f1=new Font("ÂÞÂí",Font.BOLD,20); // font
		this.setSize(800, 600); // set size
		this.setLocation(500, 200); // set location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		JLabel j1 = new JLabel("Recommedned recipes");
		this.add(j1);
		j1.setFont(f1);
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
		 
		 for(int i=0;i<recipeList.size();i++) { // for all recipe in list
			 JCheckBox j = new JCheckBox(recipeList.get(i));
			 jbStore.add(j); // add JCheckBox
			 p.add(j);
		 }
	
	     JScrollPane   sp   =   new  JScrollPane(p); // JScrollPane
	     sp.setPreferredSize(new Dimension(100,120)); // set PreferredSize
	     this.getContentPane().add(sp,BorderLayout.LINE_END); 
	        
		JButton b1 = new JButton("Back");  // button1
		JButton b2 = new JButton("Select");// button2
		JButton b3 = new JButton("Recipe with missing");// button3
		b1.setFont(f1);b2.setFont(f1);b3.setFont(f1); // font
		this.add(b1); // add button1
		this.add(b2); // add button2
		this.add(b3); // add button3
		

		b1.addActionListener(new ActionListener() {	// add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event 
				closeThis();
				new InputUI();
			}
		});
		
		b3.addActionListener(new ActionListener() {	 // add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event 
				closeThis();
				try {
					new RecipeMissingSomeUI(batchSize);
				} catch (ClassNotFoundException | SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {	 // add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event 
				int status = 0;
				String first = null;
				 for(int i=0;i<jbStore.size();i++) { // for all recipe
					 if(jbStore.get(i).isSelected()) {
						 if(first == null)
							 first = jbStore.get(i).getText(); // get the text
						 status+=1;
					 }
				 }
				 if(status==0) { // no selecting
					 JOptionPane.showMessageDialog(null, "You are supposed to select one recipe!", "Warning",JOptionPane.WARNING_MESSAGE); 
				 }
				 else if(status==1) { // select 1 box 
					 closeThis();
					 new RecipeName(first,batchSize);
				 }
				 else { // select more than 1 box
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

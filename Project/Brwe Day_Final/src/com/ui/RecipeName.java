package com.ui;
// import packages
import mainPart.*;
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
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import com.controller.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.dataBase.DataBase;


public class RecipeName extends View { // this class extends View
	private ControllerForBrewRecipe controllerFBR;
	private Recipe recipe;
	public RecipeName(String recipeName, double batchSize) {
		this.setTitle("Recommend Recipe Ingredient"); //set title
		this.setSize(800, 600); // set size
		this.setLocation(500, 200); // set location
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		JPanel bittonP = new JPanel();
		Font f1=new Font("ÂÞÂí",Font.BOLD,20); // font
		JLabel j1 = new JLabel("Recipe name ");
		j1.setFont(f1);
		p.add(j1);
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new FlowLayout()); // set layout
		p.setLayout(new GridLayout(6, 1, 30,20));
	
		recipe = new Recipe(); // recipe
		recipe = recipe.getRecipe(recipeName); // get the recipe
		Map<String,Double>recipeList = recipe.convertValue(recipeName, batchSize, "ml", "g");
		JLabel jj1 = new JLabel(recipeName+":");
		jj1.setFont(f1);
		p.add(jj1);
		for(String key:recipeList.keySet()) {
			BigDecimal bg = new BigDecimal(recipeList.get(key)).setScale(2, RoundingMode.UP);
			JLabel jj2 = new JLabel(key+" needs "+bg+" g.");
			jj2.setFont(f1);
			p.add(jj2);
		}
		
		this.add(p); // Add panel to the frame.
		
		JButton b1 = new JButton("Back");
		b1.setFont(f1); // set font of button1
		JButton b2 = new JButton("Brew");
		b2.setFont(f1); // set font of button2
		bittonP.add(b1); // add button1
		bittonP.add(b2); // add button2
		p.add(bittonP);
		
		b1.addActionListener(new ActionListener() {	// add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event
				closeThis();
				try {
					new RecommendRecipe(batchSize); 
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		b2.addActionListener(new ActionListener() {	// add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event
				controllerFBR = new ControllerForBrewRecipe(); // controller
				controllerFBR.conForBrewRecipe(recipeName, batchSize,"ml","g");
				JOptionPane.showMessageDialog(null, "Successfully brewing!", "Brewing recipe",JOptionPane.INFORMATION_MESSAGE); 
				int result = JOptionPane.showConfirmDialog(null, "Do you want to wirte note for this brew?",    "Write note decision", JOptionPane.YES_NO_OPTION); 
				JOptionPane.setDefaultLocale(Locale.ENGLISH);
				if(result==JOptionPane.YES_OPTION) {
					try {
						new OneNotePageUI();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					closeThis(); // c;ose
				}
				else {
					closeThis(); // close
					new InputUI();
				}
			}
		});
		
		
		this.setVisible(true);// set to visible
		
	
	}
	protected void closeThis() {
		this.setVisible(false); // close
	}
	
}

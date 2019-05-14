package com.ui;
import mainPart.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.dataBase.DataBase;
import com.exception.LargerThanEquip;

public class RecipeName extends JFrame {
	private ControllerForBrewRecipe controllerFBR;
	private Recipe recipe;
	public RecipeName(String recipeName, double batchSize) {
		this.setTitle("AddIngredientUI");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(4, 1, 20, 10));
		
		recipe = new Recipe();
		recipe = recipe.getRecipe(recipeName);
		Map<String,Double>recipeList = recipe.convertValue(recipeName, batchSize, "ml", "g");
		p.add(new JLabel(recipeName+":"));
		for(String key:recipeList.keySet()) {
			 BigDecimal bg = new BigDecimal(recipeList.get(key)).setScale(2, RoundingMode.UP);
			p.add(new JLabel(key+" needs "+bg+" g."));
		}
		
		
		this.add(new JLabel("Recipe name"));
		this.add(p); // Add panel to the frame.
		JButton b1 = new JButton("Back");
		JButton b2 = new JButton("Brew");
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
				controllerFBR = new ControllerForBrewRecipe();
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
					} catch (LargerThanEquip e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					closeThis();
				}
				else {
					closeThis();
					new InputUI();
				}
			}
		});
		
		
		this.setVisible(true);
		
	
	}
	protected void closeThis() {
		this.setVisible(false);
	}
	
}

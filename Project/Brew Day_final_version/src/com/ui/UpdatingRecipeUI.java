package com.ui;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.dataBase.DataBase;

import mainPart.Recipe;
import mainPart.RecipeIngredient;
 
public class UpdatingRecipeUI extends View{
	private Recipe recipe;
	public UpdatingRecipeUI() throws SQLException{
		this.setTitle("View/Updating a Recipe");
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,300,600,400);
		JPanel contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		this.setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		JLabel label=new JLabel("List of recipe:");
		contentPane.add(label);
		JComboBox comboBox=new JComboBox();
		recipe = new Recipe();
		ArrayList<String> recipeList = recipe.getAllRecipeName();
		for(int i=0;i<recipeList.size();i++) {
			comboBox.addItem(recipeList.get(i));
		}
		contentPane.add(comboBox);
		
		Font f=new Font("ÂÞÂí",Font.BOLD,20);
		
		JButton b1 = new JButton("Back");
		JButton b2 = new JButton("Continue");
		JLabel j1 = new JLabel();
		JLabel j2 = new JLabel();
		JLabel j3 = new JLabel();
		JLabel j4 = new JLabel();
		JLabel j5 = new JLabel();
		JLabel j6 = new JLabel();
		
		b1.setFont(f);b2.setFont(f);label.setFont(f);comboBox.setFont(f);
		j1.setFont(f);j2.setFont(f);j3.setFont(f);j4.setFont(f);j5.setFont(f);j6.setFont(f);
		// Get the initial displaying recipe
		recipe = recipe.getRecipe(recipeList.get(0));
		
		// Initial the content from the first recipe 
		j1.setText("liter:"+recipe.getQuantity()+"ml");
		Map<String, RecipeIngredient> ingredientList = recipe.getIngredients();
		double i1=0;
		if(ingredientList.containsKey("malts"))
			i1 = ingredientList.get("malts").getAmount();
		j2.setText("malts:"+i1+"g");
		double i2=0;
		if(ingredientList.containsKey("hops"))
			i2 = ingredientList.get("hops").getAmount();
		j3.setText("hops:"+i2+"g");
		double i3=0;
		if(ingredientList.containsKey("yeasts"))
			i3 = ingredientList.get("yeasts").getAmount();
		j4.setText("yeasts:"+i3+"g");
		double i4=0;
		if(ingredientList.containsKey("sugars"))
			i4 = ingredientList.get("sugars").getAmount();
		j5.setText( "sugars:"+i4+"g");
		double i5=0;
		if(ingredientList.containsKey("additives"))
			i5 = ingredientList.get("additives").getAmount();
		j6.setText( "additives:"+i5+"g");
		
		
		
		// Draw box connection with content
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox combo = (JComboBox) e.getSource();
				recipe = recipe.getRecipe((String)combo.getSelectedItem());
				j1.setText("liter:"+recipe.getQuantity()+"ml");
				Map<String, RecipeIngredient> ingredientList = recipe.getIngredients();
				double i1=0;
				if(ingredientList.containsKey("malts"))
					i1 = ingredientList.get("malts").getAmount();
				j2.setText("malts:"+i1+"g");
				double i2=0;
				if(ingredientList.containsKey("hops"))
					i2 = ingredientList.get("hops").getAmount();
				j3.setText("hops:"+i2+"g");
				double i3=0;
				if(ingredientList.containsKey("yeasts"))
					i3 = ingredientList.get("yeasts").getAmount();
				j4.setText("yeasts:"+i3+"g");
				double i4=0;
				if(ingredientList.containsKey("sugars"))
					i4 = ingredientList.get("sugars").getAmount();
				j5.setText( "sugars:"+i4+"g");
				double i5=0;
				if(ingredientList.containsKey("additives"))
					i5 = ingredientList.get("additives").getAmount();
				j6.setText( "additives:"+i5+"g");
			}
		});
		
		
		// Cancel
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainRecipeUI();
			}
		});
		// Continue
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				// Get the selected item from the box
				new UpdatingRecipeModificationUI((String) comboBox.getSelectedItem());
			}
		});
		
		contentPane.add(b1);
		contentPane.add(b2);
		
		contentPane.add(j1);
		contentPane.add(j2);
		contentPane.add(j3);
		contentPane.add(j4);
		contentPane.add(j5);
		contentPane.add(j6);
		
		this.setVisible(true);
	}
    protected void closeThis() {
		this.setVisible(false);
		
	}

}
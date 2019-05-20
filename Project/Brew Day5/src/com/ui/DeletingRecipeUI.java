package com.ui;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.controller.ControllerDeletingRecipe;
import com.dataBase.DataBase;

import mainPart.Recipe;
import mainPart.RecipeIngredient;
 
public class DeletingRecipeUI extends JFrame{
	private Recipe recipe;
	ControllerDeletingRecipe controllerDR;
	public DeletingRecipeUI() throws SQLException{
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setTitle("Delete a Recipe");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(500,300,600,400);
		
		recipe = new Recipe();
		
		JPanel contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setBounds(10, 10, 500, 200);
		contentPane.setLayout(new GridLayout(7,1,10,10));
		
		this.setContentPane(contentPane);
		
		
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,30,5));
		JLabel label=new JLabel("List of recipe:");
		contentPane.add(label);
		JComboBox comboBox=new JComboBox();
		ArrayList<String> recipeList = recipe.getAllRecipeName();
		for(int i=0;i<recipeList.size();i++) {
			comboBox.addItem(recipeList.get(i));
		}
		contentPane.add(comboBox);
		
		Font f=new Font("ÂÞÂí",Font.BOLD,20);
		
		JButton b1 = new JButton("Back");
		JButton b2 = new JButton("Delete");
		JLabel j1 = new JLabel();
		JLabel j2 = new JLabel();
		JLabel j3 = new JLabel();
		JLabel j4 = new JLabel();
		JLabel j5 = new JLabel();
		JLabel j6 = new JLabel();
		
		b1.setFont(f);b2.setFont(f);label.setFont(f);comboBox.setFont(f);
		j1.setFont(f);j2.setFont(f);j3.setFont(f);j4.setFont(f);j5.setFont(f);j6.setFont(f);
		
		// Store the name of recipe 
		recipe = recipe.getRecipe(recipeList.get(0));
		
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
		
		
		
		// Down list connection
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
		
		
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainRecipeUI();
			}
		});
		// Button for deleting the recipe
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controllerDR = new ControllerDeletingRecipe();
					controllerDR.ConDeletingRecipe((String) comboBox.getSelectedItem());
					JOptionPane.showMessageDialog(null, "Successfully Delete the recipe"+" \""+(String) comboBox.getSelectedItem()+"\"", "Recipe Deleting",JOptionPane.INFORMATION_MESSAGE); 
					closeThis();
					new DeletingRecipeUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	
		
		contentPane.add(comboBox);
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
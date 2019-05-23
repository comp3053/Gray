package com.ui;
// import packages
import mainPart.*;
import java.awt.*;
import javax.swing.*;
import com.controller.*;
import com.dataBase.DataBase;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Map;

public class UpdatingRecipeModificationUI extends View {
	Recipe recipe;
	private ControllerUpdatingRecipe controllerUR;
	public  UpdatingRecipeModificationUI(String selectedItem){
		Font f=new Font("罗马",Font.BOLD,20); // font
		int gap = 100; // gap of the surface
		this.setTitle("Updating a Recipe");
		this.setSize(800, 600); // set size
		this.setLocation(500, 200); // set location
		this.setLayout(null);
		recipe = new Recipe();
		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 500, 200);
		pInput.setLayout(new GridLayout(8,1,10,0));
		// Get the current recipe
		recipe = recipe.getRecipe(selectedItem);
		// Get the ingredient for recipe
		Map<String,RecipeIngredient> ingredientList = recipe.getIngredients();
		// Recipe Name
		JLabel hint = new JLabel("Recipe "+"\""+selectedItem+"\"");
		JLabel name = new JLabel("New value:");
		JLabel value = new JLabel("Current value");
		hint.setFont(f);name.setFont(f);value.setFont(f);
		JLabel beer = new JLabel("Liter of beer:");
		JTextField beerText = new JTextField();
		JLabel beerNewValue = new JLabel(recipe.getQuantity()+" ml");
		beer.setFont(f);
		beerNewValue.setFont(f);
		//whether the receipe contains malts 
		JLabel malts = new JLabel("Malts:");
		JTextField maltsText = new JTextField();
		double i1=0;
		if(ingredientList.containsKey("malts"))
			i1 = ingredientList.get("malts").getAmount();
		JLabel maltsNewValue = new JLabel(i1+" g");
		malts.setFont(f);
		maltsNewValue.setFont(f);
		//whether the receipe contains hops 
		JLabel hops = new JLabel("Hops:");
		JTextField hopsText = new JTextField();
		double i2=0;
		if(ingredientList.containsKey("hops"))
			i2 = ingredientList.get("hops").getAmount();
		JLabel hopsNewValue = new JLabel(i2+" g");
		hops.setFont(f);
		hopsNewValue.setFont(f);
		//whether the receipe contains yeasts 
		JLabel yeasts = new JLabel("Yeasts:");
		JTextField yeastsText = new JTextField();
		double i3=0;
		if(ingredientList.containsKey("yeasts"))
			i3 = ingredientList.get("yeasts").getAmount();
		JLabel yeastsNewValue = new JLabel(i3+" g");
		yeasts.setFont(f);
		yeastsNewValue.setFont(f);
		//whether the receipe contains sugars 
		JLabel sugars = new JLabel("Sugars:");
		JTextField sugarsText = new JTextField();
		double i4=0;
		if(ingredientList.containsKey("sugars"))
			i4 = ingredientList.get("sugars").getAmount();
		JLabel sugarsNewValue = new JLabel(i4+" g");
		sugars.setFont(f);
		sugarsNewValue.setFont(f);
		//whether the receipe contains additives 
		JLabel additives = new JLabel("Additives:");
		JTextField additivesText = new JTextField();
		double i5=0;
		if(ingredientList.containsKey("additives"))
			i5 = ingredientList.get("additives").getAmount();
		JLabel additivesNewValue = new JLabel(i5+" g");
		additives.setFont(f);
		additivesNewValue.setFont(f);

		// define 2 buttons: update, cancle
		JButton b1 = new JButton("Update");
		JButton b2 = new JButton("Cancle");

		
		b1.addActionListener(new ActionListener() {	// add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event
				// Check for the liter
				String check=null;
				controllerUR = new ControllerUpdatingRecipe();
				check = controllerUR.conUpdatingRecipe(selectedItem, beerText.getText(), maltsText.getText(), hopsText.getText(), 
						yeastsText.getText(), sugarsText.getText(),additivesText.getText());
				if(beerText.getText().length()==0 && maltsText.getText().length()==0 && hopsText.getText().length()==0 && yeastsText.getText().length()==0
						&& sugarsText.getText().length()==0 && additivesText.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "You have updated nothing!", "Warning",JOptionPane.WARNING_MESSAGE); 
				}
				else if(check.equals("invalid"))  // error input 1: invalid
					JOptionPane.showMessageDialog(null, "Input should be number!", "Warning",JOptionPane.WARNING_MESSAGE); 
				else if(check.equals("less than 0")) // error input 2: <0
					JOptionPane.showMessageDialog(null, "The input number should be larger than 0", "Warning",JOptionPane.WARNING_MESSAGE); 
				else if(check.equals("good")) { // correct input!!!
					JOptionPane.showMessageDialog(null, "Successfully Updating", "Recipe Updating",JOptionPane.INFORMATION_MESSAGE); 
					closeThis();
					new UpdatingRecipeModificationUI(selectedItem);
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {	// add action listener
			@Override
			public void actionPerformed(ActionEvent e) { // add action event 
				closeThis(); // close
				try {
					new UpdatingRecipeUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		b1.setFont(f);b2.setFont(f);
		
		pInput.add(hint); // add to pInput of JPanel
		pInput.add(name);
		pInput.add(value);
		
		pInput.add(beer); // add to pInput of JPanel
		pInput.add(beerText);
		pInput.add(beerNewValue);
		
		pInput.add(malts); // add to pInput of JPanel
		pInput.add(maltsText);
		pInput.add(maltsNewValue);
		
		pInput.add(hops); // add to pInput of JPanel
		pInput.add(hopsText);
		pInput.add(hopsNewValue);
				
		pInput.add(yeasts); // add to pInput of JPanel
		pInput.add(yeastsText);
		pInput.add(yeastsNewValue);
		
		pInput.add(sugars); // add to pInput of JPanel
		pInput.add(sugarsText);
		pInput.add(sugarsNewValue);
		
		
		pInput.add(additives); // add to pInput of JPanel
		pInput.add(additivesText);
		pInput.add(additivesNewValue);
		
		b1.setBounds(150, 380, 120, 50);
		b2.setBounds(350, 380, 120, 50);

		
		this.add(pInput); // add pInput
		this.add(b1); // add button 1
		this.add(b2);// add button 2
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setVisible(true); // set to visible 

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false); // close
	}

}



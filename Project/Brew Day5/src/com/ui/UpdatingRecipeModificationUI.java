package com.ui;
import mainPart.*;
import java.awt.*;
import javax.swing.*;
import com.controller.*;
import com.dataBase.DataBase;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Map;

public class UpdatingRecipeModificationUI extends JFrame {
	Recipe recipe;
	private ControllerUpdatingRecipe controllerUR;
	public  UpdatingRecipeModificationUI(String selectedItem){
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Font f=new Font("罗马",Font.BOLD,20);
		int gap = 100;
		this.setTitle("Updating a Recipe");
		this.setSize(800, 600);
		this.setLocation(500, 200);
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
		//!!!
		JLabel malts = new JLabel("Malts:");
		JTextField maltsText = new JTextField();
		double i1=0;
		if(ingredientList.containsKey("malts"))
			i1 = ingredientList.get("malts").getAmount();
		JLabel maltsNewValue = new JLabel(i1+" g");
		malts.setFont(f);
		maltsNewValue.setFont(f);
		//!!!
		JLabel hops = new JLabel("Hops:");
		JTextField hopsText = new JTextField();
		double i2=0;
		if(ingredientList.containsKey("hops"))
			i2 = ingredientList.get("hops").getAmount();
		JLabel hopsNewValue = new JLabel(i2+" g");
		hops.setFont(f);
		hopsNewValue.setFont(f);
		//!!!
		JLabel yeasts = new JLabel("Yeasts:");
		JTextField yeastsText = new JTextField();
		double i3=0;
		if(ingredientList.containsKey("yeasts"))
			i3 = ingredientList.get("yeasts").getAmount();
		JLabel yeastsNewValue = new JLabel(i3+" g");
		yeasts.setFont(f);
		yeastsNewValue.setFont(f);
		//!!!
		JLabel sugars = new JLabel("Sugars:");
		JTextField sugarsText = new JTextField();
		double i4=0;
		if(ingredientList.containsKey("sugars"))
			i4 = ingredientList.get("sugars").getAmount();
		JLabel sugarsNewValue = new JLabel(i4+" g");
		sugars.setFont(f);
		sugarsNewValue.setFont(f);
		//!!!
		JLabel additives = new JLabel("Additives:");
		JTextField additivesText = new JTextField();
		double i5=0;
		if(ingredientList.containsKey("additives"))
			i5 = ingredientList.get("additives").getAmount();
		JLabel additivesNewValue = new JLabel(i5+" g");
		additives.setFont(f);
		additivesNewValue.setFont(f);

		JButton b1 = new JButton("Update");
		JButton b2 = new JButton("Cancle");

		
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Check for the liter
				String check=null;
				controllerUR = new ControllerUpdatingRecipe();
				check = controllerUR.conUpdatingRecipe(selectedItem, beerText.getText(), maltsText.getText(), hopsText.getText(), 
						yeastsText.getText(), sugarsText.getText(),additivesText.getText());
				if(beerText.getText().length()==0 && maltsText.getText().length()==0 && hopsText.getText().length()==0 && yeastsText.getText().length()==0
						&& sugarsText.getText().length()==0 && additivesText.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "You have updated nothing!", "Warning",JOptionPane.WARNING_MESSAGE); 
				}
				else if(check.equals("invalid")) 
					JOptionPane.showMessageDialog(null, "Input should be number!", "Warning",JOptionPane.WARNING_MESSAGE); 
				else if(check.equals("less than 0"))
					JOptionPane.showMessageDialog(null, "The input number should be larger than 0", "Warning",JOptionPane.WARNING_MESSAGE); 
				else if(check.equals("good")) {
					JOptionPane.showMessageDialog(null, "Successfully Updating", "Recipe Updating",JOptionPane.INFORMATION_MESSAGE); 
					closeThis();
					new UpdatingRecipeModificationUI(selectedItem);
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				try {
					new UpdatingRecipeUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		b1.setFont(f);b2.setFont(f);
		
		pInput.add(hint);
		pInput.add(name);
		pInput.add(value);
		
		pInput.add(beer);
		pInput.add(beerText);
		pInput.add(beerNewValue);
		
		pInput.add(malts);
		pInput.add(maltsText);
		pInput.add(maltsNewValue);
		
		pInput.add(hops);
		pInput.add(hopsText);
		pInput.add(hopsNewValue);
				
		pInput.add(yeasts);
		pInput.add(yeastsText);
		pInput.add(yeastsNewValue);
		
		
		pInput.add(sugars);
		pInput.add(sugarsText);
		pInput.add(sugarsNewValue);
		
		
		pInput.add(additives);
		pInput.add(additivesText);
		pInput.add(additivesNewValue);
		
		b1.setBounds(150, 380, 120, 50);
		b2.setBounds(350, 380, 120, 50);

		
		this.add(pInput);
		this.add(b1);
		this.add(b2);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		this.setVisible(true);

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}

}



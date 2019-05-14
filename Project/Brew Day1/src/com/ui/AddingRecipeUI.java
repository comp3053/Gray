package com.ui;
import com.dataBase.*;
import mainPart.*;
import com.controller.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class AddingRecipeUI extends JFrame{
	private ControllerAddingRecipe controllerAR;
	public AddingRecipeUI()  {
		int gap = 100;
		this.setTitle("Adding a Recipe");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 500, 200);
		pInput.setLayout(new GridLayout(7,1,10,10));

		JLabel recipeName = new JLabel("Name of Recipe:");
		JTextField recipeNameText = new JTextField();

		JLabel location = new JLabel("Liter of beer(l):");
		JTextField locationText = new JTextField();


		JLabel type = new JLabel("Malts(g):");
		JTextField maltText = new JTextField();


		JLabel name = new JLabel("Hops(g):");
		JTextField hopText = new JTextField();


		JLabel bossname = new JLabel("Yeasts(g):");
		JTextField yeastText = new JTextField();


		JLabel money = new JLabel("Sugars(g):");
		JTextField sugarText = new JTextField();


		JLabel product = new JLabel("Additives(g):");
		JTextField additiveText = new JTextField();


		JButton b1 = new JButton("Add Receipe");
		JButton b2 = new JButton("Back");

		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// Check for the quantity input
				String name = recipeNameText.getText();
				int liter=0; 
				try{
					liter=Integer.parseInt(locationText.getText());
				}catch(Throwable e1){
					liter=-1; 
				}
				// Check for the input ingredient
				int malt=-1,hop=-1,yeast=-1,sugar=-1,additive=-1;
				if(maltText.getText().length()!=0) {
					try{
						malt=Integer.parseInt(maltText.getText());
					}catch(Throwable e1){
						malt=-1; 
					}
				}
				if(hopText.getText().length()!=0) {
					try{
						hop=Integer.parseInt(hopText.getText());
					}catch(Throwable e1){
						hop=-1; 
					}
				}
				if(yeastText.getText().length()!=0) {
					try{
						yeast=Integer.parseInt(yeastText.getText());
					}catch(Throwable e1){
						yeast=-1; 
					}
				}
				if(sugarText.getText().length()!=0) {
					try{
						sugar=Integer.parseInt(sugarText.getText());
					}catch(Throwable e1){
						sugar=-1; 
					}
				}
				if(additiveText.getText().length()!=0) {
					try{
						additive=Integer.parseInt(additiveText.getText());
					}catch(Throwable e1){
						additive=-1; 
					}
				}
			
				controllerAR = new ControllerAddingRecipe();
				controllerAR.conAddingRecipe(name, liter, malt, hop, yeast, sugar, additive);
				JOptionPane.showMessageDialog(null, "Successfully Adding", "Recipe Adding",JOptionPane.INFORMATION_MESSAGE); 
		}});


		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainRecipeUI();
			}
		});

		pInput.add(recipeName);
		pInput.add(recipeNameText);
		pInput.add(location);
		pInput.add(locationText);
		pInput.add(type);
		pInput.add(maltText);
		pInput.add(name);
		pInput.add(hopText);
		pInput.add(bossname);
		pInput.add(yeastText);
		pInput.add(money);
		pInput.add(sugarText);
		pInput.add(product);
		pInput.add(additiveText);

		b1.setBounds(80, 380, 200, 50);
		b2.setBounds(340, 380, 200, 50);


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




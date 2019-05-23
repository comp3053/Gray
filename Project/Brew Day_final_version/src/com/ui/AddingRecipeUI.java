package com.ui;
import com.dataBase.*;
import mainPart.*;
import com.controller.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
public class AddingRecipeUI extends View{
	// Get the model information from recipe
	private Recipe r;
	// Action was able to send to the controller to handle
	private ControllerAddingRecipe controllerAR;
	public AddingRecipeUI()  {
		// UI setting
		int gap = 100;
		this.setTitle("Adding a Recipe");
		this.setSize(800, 600);
		this.setLocation(500, 200);
		this.setLayout(null);
		// UI panel set
		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 500, 200);
		pInput.setLayout(new GridLayout(7,1,100,0));
		// Font setting
		Font f=new Font("罗马",Font.BOLD,20);
		// Recipe information set
		JLabel recipeName = new JLabel("Name of Recipe:");
		JTextField recipeNameText = new JTextField();
		recipeName.setFont(f);
		
		JLabel location = new JLabel("Liter of beer(ml):");
		JTextField locationText = new JTextField();
		location.setFont(f);

		JLabel type = new JLabel("Malts(g):");
		JTextField maltText = new JTextField();
		type.setFont(f);

		JLabel name = new JLabel("Hops(g):");
		JTextField hopText = new JTextField();
		name.setFont(f);

		JLabel bossname = new JLabel("Yeasts(g):");
		JTextField yeastText = new JTextField();
		bossname.setFont(f);

		JLabel money = new JLabel("Sugars(g):");
		JTextField sugarText = new JTextField();
		money.setFont(f);

		JLabel product = new JLabel("Additives(g):");
		JTextField additiveText = new JTextField();
		product.setFont(f);
		// Button set
		JButton b1 = new JButton("Add Receipe");
		JButton b2 = new JButton("Back");
		// Button style setting
		b1.setPreferredSize(new Dimension(100,50));
		b1.setFont(f);b2.setFont(f);
		
		// Button action setting
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = recipeNameText.getText();
				r = new Recipe();
				// Empty input checking
				if(name.length()==0) {
					JOptionPane.showMessageDialog(null, "Recipe Name is required!", "Warning",JOptionPane.WARNING_MESSAGE); 
				}
				// Other input requirement checking
				else if(name.length()!=0){
					ArrayList<String> recipeList=null;
					try {
						recipeList = r.getAllRecipeName();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(recipeList.contains(name)) {
						// Waring information for already exists recipe name
						JOptionPane.showMessageDialog(null, "Recipe Name "+name+" has already exists!", "Warning",JOptionPane.WARNING_MESSAGE);
					}
					else if(locationText.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "Recipe liter is required!", "Warning",JOptionPane.WARNING_MESSAGE);
					}
					else if(maltText.getText().length()==0 && hopText.getText().length()==0 && yeastText.getText().length()==0
							&& sugarText.getText().length()==0 && additiveText.getText().length()==0) {
						JOptionPane.showMessageDialog(null, "Recipe should have a least one ingredient!", "Warning",JOptionPane.WARNING_MESSAGE);
					}
					// Pass all the error test and send the action information to controller
					else {
						controllerAR = new ControllerAddingRecipe();
						String check = controllerAR.conAddingRecipe(name, locationText.getText(), maltText.getText(), hopText.getText(), yeastText.getText(), sugarText.getText(),additiveText.getText());
						if(check.equals("invalid")) 
							JOptionPane.showMessageDialog(null, "Input should be number!", "Warning",JOptionPane.WARNING_MESSAGE); 
						else if(check.equals("less than 0"))
							JOptionPane.showMessageDialog(null, "The input number should be larger than 0", "Warning",JOptionPane.WARNING_MESSAGE); 
						else if(check.equals("good")) {
							JOptionPane.showMessageDialog(null, "Successfully Adding Recipe"+" \""+name+"\" ", "Recipe Adding",JOptionPane.INFORMATION_MESSAGE); 
							closeThis();
							new MaintainRecipeUI();
						}
					}
				}
				
			
			}});

		// Back to the last page
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




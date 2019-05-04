
package com.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class UpdatingRecipeModificationUI extends JFrame {
	public  UpdatingRecipeModificationUI(){
		int gap = 100;
		this.setTitle("Adding a Recipe");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);
		
		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 500, 200);
		pInput.setLayout(new GridLayout(8,1,10,10));
		
		JLabel hint = new JLabel("The current recipe is xxxxxx");
		JLabel name = new JLabel("New value of ingredient:");
		JLabel value = new JLabel("Current value");
		
		JLabel beer = new JLabel("Liter of beer:");
		JTextField beerText = new JTextField();
		JLabel beerNewValue = new JLabel("xxx L");


		JLabel malts = new JLabel("Malts:");
		JTextField maltsText = new JTextField();
		JLabel maltsNewValue = new JLabel("xxx kg");


		JLabel hops = new JLabel("Hops:");
		JTextField hopsText = new JTextField();
		JLabel hopsNewValue = new JLabel("xxx kg");


		JLabel yeasts = new JLabel("Yeasts:");
		JTextField yeastsText = new JTextField();
		JLabel yeastsNewValue = new JLabel("xxx kg");


		JLabel sugars = new JLabel("Sugars:");
		JTextField sugarsText = new JTextField();
		JLabel sugarsNewValue = new JLabel("xxx kg");


		JLabel additives = new JLabel("Additives:");
		JTextField additivesText = new JTextField();
		JLabel additivesNewValue = new JLabel("xxx kg");


		JButton b1 = new JButton("Add Receipe");
		JButton b2 = new JButton("Cancle");

		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new UpdatingRecipeUI();
			}
		});
		
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



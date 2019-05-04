package com.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RecipeMissingSomeUI extends JFrame {

	public RecipeMissingSomeUI() {
		this.setTitle("RecipeMissingSomeUI");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(4, 1, 20, 10));
		
		p.add(new JLabel("Recipe 4 missing ingredient 1"));
		p.add(new JLabel("Recipe 5 missing ingredient 1"));
		p.add(new JLabel("Recipe 6 missing ingredient 1"));
		p.add(new JLabel("Recipe 7 missing ingredient 1"));
		
		
		this.add(new JLabel("Recipe missing some inngredient"));
		this.add(p); // Add panel to the frame.
		JButton b1 =new JButton("Change the input");
		this.add(b1);
		
		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new InputUI();
			}
		});
		
		
		this.setVisible(true);
		
	
	}
	
	
	protected void closeThis() {
		this.setVisible(false);
	}
	
}
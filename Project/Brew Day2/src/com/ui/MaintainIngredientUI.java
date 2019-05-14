package com.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;


public class MaintainIngredientUI extends JFrame {

	public MaintainIngredientUI() {
		this.setTitle("MainPageUI");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		this.add(p); // Add panel to the frame.
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(3, 1, 20, 10));
		// Create four buttons and add them to the panel (not the frame).
		// The four buttons always stay together even when you resize.
		JButton j1 = new JButton("Add amount of Existing Ingredient");
		JButton j2 = new JButton("Subtract amount of Existing Ingredient" );
		JButton j3 = new JButton("Back to the Home Page");
		
		p.add(j1);p.add(j2);p.add(j3);
		
		j1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				try {
					new AddIngredient();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		
		
		
		j2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				try {
					new SubstractIngredient();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		
		j3.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MainPageView();
			}

		});

		this.setVisible(true);
	
	}

	protected void closeThis() {
		this.setVisible(false);
	}
}

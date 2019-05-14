package com.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;



import javax.swing.JLabel;
import javax.swing.JTextField;



public class SubstractIngredient extends JFrame {

	public SubstractIngredient() {
	
	this.setTitle("AddIngredientUI");
	this.setSize(800, 600);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new FlowLayout()); // Layout manager for the frame
	
	JPanel p = new JPanel();
	p.setBackground(Color.WHITE);
	this.add(new JLabel("Update ingredient:"));
	this.add(p); // Add panel to the frame.

	
	// Layout manager for the panel.
	// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
	p.setLayout(new GridLayout(6, 3, 20, 10));
	
	
	JLabel malts = new JLabel("Malts:       200gram");
	JTextField text1 = new JTextField();
	JTextField text2 = new JTextField();
	JTextField text3 = new JTextField();
	JTextField text4 = new JTextField();
	JTextField text5 = new JTextField();
	JTextField text6 = new JTextField();
	JTextField text7 = new JTextField();
	JTextField text8 = new JTextField();
	JTextField text9 = new JTextField();
	JTextField text10 = new JTextField();
	JLabel gram1 = new JLabel("gram");
	JLabel gram2 = new JLabel("gram");
	JLabel gram3 = new JLabel("gram");
	JLabel gram4 = new JLabel("gram");
	JLabel gram5 = new JLabel("gram");
	JLabel gram6 = new JLabel("gram");
	JLabel gram7 = new JLabel("gram");
	JLabel gram8 = new JLabel("gram");
	JLabel gram9 = new JLabel("gram");
	JLabel gram10 = new JLabel("gram");
	
	
	
	JLabel hops = new JLabel("Hops:       200gram");
	
	JLabel yeasts = new JLabel("Yeasts:       200gram");
	
	JLabel suger = new JLabel("Surger:       200gram");
	
	JLabel additives = new JLabel("Additives:       200gram");
	
	
	JLabel current = new JLabel("Current value");
	JLabel add = new JLabel("Add");
	JLabel minus = new JLabel("Minus");
	JLabel space1 = new JLabel(" ");
	JLabel space2 = new JLabel(" ");
	
	
	p.add(current);
	p.add(minus);
	p.add(space1);
	
	
	
	p.add(malts);
	p.add(text1);
	p.add(gram1);
	
	p.add(hops);	
	p.add(text3);
	p.add(gram3);
	
	
	p.add(yeasts);	
	p.add(text5);
	p.add(gram5);
		
	
	
	p.add(suger);	
	p.add(text7);
	p.add(gram7);
	
	
	
	p.add(additives);	
	p.add(text9);
	p.add(gram9);
		
	JButton b1 = new JButton("Update");
	JButton b2 = new JButton("Cancel");
	this.add(b1);
	this.add(b2);
	
	b1.addActionListener(new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

	});
	
	b2.addActionListener(new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			closeThis();
			new MaintainIngredientUI();
		}

	});
	
	
	this.setVisible(true);
	
	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

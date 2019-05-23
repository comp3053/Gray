package com.ui;

// import packages
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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



public class UpdateIngredientUI extends View { // this class extends from View

	public UpdateIngredientUI() {
	this.setTitle("AddIngredientUI"); // set title
	this.setSize(800, 600); // set size
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLayout(new FlowLayout()); // Layout manager for the frame
	
	JPanel p = new JPanel(); // create a new JPanel
	p.setBackground(Color.WHITE); // background color
	this.add(new JLabel("Update ingredient:"));
	this.add(p); // Add panel to the frame.

	
	// Layout manager for the panel.
	// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
	p.setLayout(new GridLayout(6, 5, 20, 10));
	
	
	JLabel malts = new JLabel("Malts:       200gram");
	JTextField text1 = new JTextField(); // JTextField
	JTextField text2 = new JTextField();
	JTextField text3 = new JTextField();
	JTextField text4 = new JTextField();
	JTextField text5 = new JTextField();
	JTextField text6 = new JTextField();
	JTextField text7 = new JTextField();
	JTextField text8 = new JTextField();
	JTextField text9 = new JTextField();
	JTextField text10 = new JTextField();
	JLabel gram1 = new JLabel("gram"); // JLabel
	JLabel gram2 = new JLabel("gram");
	JLabel gram3 = new JLabel("gram");
	JLabel gram4 = new JLabel("gram");
	JLabel gram5 = new JLabel("gram");
	JLabel gram6 = new JLabel("gram");
	JLabel gram7 = new JLabel("gram");
	JLabel gram8 = new JLabel("gram");
	JLabel gram9 = new JLabel("gram");
	JLabel gram10 = new JLabel("gram");
	
	
	
	JLabel hops = new JLabel("Hops:       200gram"); // JLabel: hops
	
	JLabel yeasts = new JLabel("Yeasts:       200gram"); // JLabel: yeasts
	
	JLabel suger = new JLabel("Surger:       200gram"); // JLabel: suger
	
	JLabel additives = new JLabel("Additives:       200gram"); // JLabel: additives
	
	
	JLabel current = new JLabel("Current value"); // JLabel: Current value
	JLabel add = new JLabel("Add"); // JLabel: add
	JLabel minus = new JLabel("Minus"); // JLabel: minus
	JLabel space1 = new JLabel(" "); // JLabel: space
	JLabel space2 = new JLabel(" "); // JLabel: space
	
	
	p.add(current); // add to JPanel: current
	p.add(add); // add to JPanel: add
	p.add(space1); // add to JPanel: space1
	p.add(minus); // add to JPanel: minus
	p.add(space2); // add to JPanel: space2
	
	
	p.add(malts); // add to JPanel: malts
	p.add(text1); // add to JPanel: text1
	p.add(gram1); // add to JPanel: gram1
	p.add(text2); // add to JPanel: text2
	p.add(gram2); // add to JPanel: gram2	
	
	p.add(hops); // add to JPanel: hops
	p.add(text3); // add to JPanel: text3
	p.add(gram3); // add to JPanel: gram3
	p.add(text4); // add to JPanel: text4
	p.add(gram4); // add to JPanel: gram4
	
	p.add(yeasts); // add to JPanel: yeats
	p.add(text5); // add to JPanel: text5
	p.add(gram5); // add to JPanel: gramm5
	p.add(text6); // add to JPanel: text6
	p.add(gram6); // add to JPanel: gram6	
	
	
	p.add(suger); // add to JPanel: sugar
	p.add(text7); // add to JPanel: text7
	p.add(gram7); // add to JPanel: gram7
	p.add(text8);// add to JPanel: text8
	p.add(gram8);// add to JPanel: gram8	
	
	
	p.add(additives);	// add to JPanel: additives
	p.add(text9); // add to JPanel: text9
	p.add(gram9); // add to JPanel: gram9
	p.add(text10);// add to JPanel: text10
	p.add(gram10);// add to JPanel: gram10
	
	this.add(new JButton("Update")); // add button 1
	this.add(new JButton("Cancel" )); // add button 2
	
	this.setVisible(true);
	
	}
}

package com.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.sun.xml.internal.ws.api.server.Container;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class MainPageView extends View {

	public MainPageView() {
		this.setTitle("MainPageUI");
		this.setSize(800, 600);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame

		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		this.add(p); // Add panel to the frame.
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(6, 1, 200, 30));
		// Create four buttons and add them to the panel (not the frame).
		// The four buttons always stay together even when you resize.
		Font f=new Font("ÂÞÂí",Font.BOLD,20);
		JButton b1 = new JButton("Maintain recipe");
		b1.setPreferredSize(new Dimension(100,50));
		JButton b2 = new JButton("Maintain ingredient" );
		JButton b3 = new JButton("Maintain equipment information");
		JButton b4 = new JButton("Write note" );
		JButton b5 = new JButton("Recommend a recipe" );
		JButton b6 = new JButton("Exit" );
		b1.setFont(f);b2.setFont(f);b3.setFont(f);b4.setFont(f);b5.setFont(f);b6.setFont(f);
		p.add(b1);p.add(b2);p.add(b3);p.add(b4);p.add(b5);p.add(b6);

		/////////////////////////////
		JLabel label = new JLabel(new ImageIcon("1.jpg"));
		LineBorder lb = new LineBorder(Color.BLACK, 5, true); 
		p.setBorder(BorderFactory.createTitledBorder(lb, "Brew Day", TitledBorder.CENTER, TitledBorder.TOP,
				new java.awt.Font("ËÎÌå",Font.HANGING_BASELINE,60)));
		/////////////////////////////


		b1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainRecipeUI();
			}
		});

		b2.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainIngredientUI();
			}
		});


		b3.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MaintainEquipmentInformationPageUI();
			}
		});


		b4.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new WriteNotePageUI();
			}
		});


		b5.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new InputUI();
			}
		});

		b6.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});


		this.setVisible(true);
	}

	protected void closeThis() {
		this.setVisible(false);
	}
}

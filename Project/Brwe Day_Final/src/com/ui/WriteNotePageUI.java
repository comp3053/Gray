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
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

public class WriteNotePageUI extends View{
	public WriteNotePageUI() {
		this.setTitle("WriteNotePageUI");
		this.setSize(800, 600);
		this.setLocation(500, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		this.add(p); // Add panel to the frame.
		// Layout manager for the panel.
		p.setLayout(new GridLayout(3, 1, 20, 30));
		// Create four buttons and add them to the panel (not the frame).
		// The four buttons always stay together even when you resize.
	
		JButton b1 = new JButton("Add New Note"); // button 1
		JButton b2 = new JButton("View/Modify Note"); // button 2
		JButton b3 = new JButton("Back Home Page"); // button 3
		Font f=new Font("����",Font.BOLD,20); // font 
		b1.setPreferredSize(new Dimension(100,50));
		b1.setFont(f);b2.setFont(f);b3.setFont(f);
		
		p.add(b1);
		p.add(b2);
		p.add(b3);
		
		b1.addActionListener(new ActionListener() {	 // add the action listener of button1
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				try {
					new NewNotePageUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		b2.addActionListener(new ActionListener() {	// add the action listener of button2
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new ModificationNotePageUI();
			}
		});
		

		b3.addActionListener(new ActionListener() {	// add the action listener of button3
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MainPageView();
			}
		});
		
		
		
		this.setVisible(true); // set to visible

	}
	
	protected void closeThis() {
		this.setVisible(false); 
	}
	
	
}

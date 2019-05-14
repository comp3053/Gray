package com.ui;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import java.awt.Container;

import javax.swing.ImageIcon;

import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;
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
public class MaintainRecipeUI extends JFrame{
	public MaintainRecipeUI() {
		this.setTitle("MainPageUI");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		//set the windows in the center
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout()); // Layout manager for the frame
		
		JPanel p = new JPanel();
		p.setBackground(Color.BLUE);
		//////////////////////////////////////////////

		ImageIcon ii=new ImageIcon("C:\\Users\\TEMP\\Desktop\\Brew Day\\src\\com\\ui/1.jpg");
		//get the picture from the document
		JLabel jl=new JLabel();//new a label
		jl.setIcon(ii);//set the icon
		this.add(jl);//add to the flame
		////////////////////////////////////////////////
		this.add(p); // Add panel to the frame.
		// Layout manager for the panel.
		// 2 rows, 2 columns, 20 pixels hgap, 10 pixels vgap.
		p.setLayout(new GridLayout(4, 1, 20, 10));
		// Create four buttons and add them to the panel (not the frame).
		// The four buttons always stay together even when you resize.
		JButton j1 = new JButton("Add Recipe");
		JButton j2 = new JButton("Update Recipe");
		JButton j3 = new JButton("Delete Recipe");
		JButton j4 = new JButton("Back to Homepage");
		p.add(j1);
		p.add(j2);
		p.add(j3);
		p.add(j4);
		
		j1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new AddingRecipeUI();
			}
		});
		
		j2.addActionListener(new ActionListener() {	
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
		
		j3.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				try {
					new DeletingRecipeUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		j4.addActionListener(new ActionListener() {	
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
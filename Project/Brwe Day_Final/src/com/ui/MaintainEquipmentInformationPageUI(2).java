package com.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MaintainEquipmentInformationPageUI extends View {
	public MaintainEquipmentInformationPageUI() {
        // UI setting
		int gap = 10;
        //set the title
		this.setTitle("Maintain Equipment");
		this.setSize(800, 600);
		this.setLocation(500, 200);
		this.setLayout(new FlowLayout());
        // Layout manager for the frame
		
		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 355, 220);
		pInput.setLayout(new GridLayout(3, 1, 20, 30));
		pInput.setBackground(Color.WHITE);
		
		// create new buttons
		JButton MaintainEquipmentInformation = new JButton("Add a Equipment Information");
		JButton DeleteEquipmentInformation = new JButton("View/Delete a Equipment Information");
		JButton BackToHomEPage = new JButton("Back to the Home Page");
		Font f=new Font("ÂÞÂí",Font.BOLD,20);
		MaintainEquipmentInformation.setPreferredSize(new Dimension(100,50));
		MaintainEquipmentInformation.setFont(f);DeleteEquipmentInformation.setFont(f);BackToHomEPage.setFont(f);

		MaintainEquipmentInformation.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				try {
					new AddUpdateEquipmentInformationPageUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		BackToHomEPage.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				new MainPageView();
			}
		});
		
		DeleteEquipmentInformation.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				closeThis();
				try {
					new subtractEquipmentInformationPageUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JTextArea ta = new JTextArea();
		ta.setLineWrap(true);
        //set the position for the buttons.
		MaintainEquipmentInformation.setBounds(30, 30 +30, 300, 40);
		DeleteEquipmentInformation.setBounds(30, 100 +30, 300, 40);
		BackToHomEPage.setBounds(30, 170 + 30, 300, 40);
		ta.setBounds(gap, 150 + 600, 375, 120);

		this.add(pInput);
		pInput.add(MaintainEquipmentInformation);
		pInput.add(DeleteEquipmentInformation);
		pInput.add(BackToHomEPage);
		//pInput.add(ta);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}

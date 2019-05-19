package com.ui;

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

public class MaintainEquipmentInformationPageUI extends JFrame {
	public MaintainEquipmentInformationPageUI() {
		int gap = 10;
		this.setTitle("maintain equipment information");
		this.setSize(800, 600);
		this.setLocation(200, 200);
		this.setLayout(null);

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap, 375, 120);
		pInput.setLayout(new GridLayout(3,1,gap,gap));
		
		
		
		JButton MaintainEquipmentInformation = new JButton("Add a Equipment Information");
		JButton DeleteEquipmentInformation = new JButton("View/Delete a Equipment Information");
		JButton BackToHomEPage = new JButton("Back to the Home Page");


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
		MaintainEquipmentInformation.setBounds(30, 30 +30, 300, 40);
		DeleteEquipmentInformation.setBounds(30, 100 +30, 300, 40);
		BackToHomEPage.setBounds(30, 170 + 30, 300, 40);
		ta.setBounds(gap, 150 + 600, 375, 120);


		this.add(pInput);
		this.add(MaintainEquipmentInformation);
		this.add(DeleteEquipmentInformation);
		this.add(BackToHomEPage);
		this.add(ta);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);

	}

	protected void closeThis() {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
